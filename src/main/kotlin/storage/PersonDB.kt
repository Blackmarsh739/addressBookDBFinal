import com.addressbook.tables.*
import com.example.addressbook.Person
import com.example.addressbook.PersonId
import com.example.addressbook.PhoneNumber
import com.example.addressbook.PhoneNumberId
import com.example.addressbook.requests.AddPersonRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID


fun ResultRow.toPerson() = Person(
    personId = this@toPerson[PersonsTable.personId],
    firstName = this@toPerson[PersonsTable.firstName],
    lastName = this@toPerson[PersonsTable.lastName]
)
object PersonDB {
    fun addPerson(person: AddPersonRequest): Person {
        val res = transaction {
            PersonsTable.insert{
                it[this.firstName] = person.firstName
                it[this.lastName] = person.lastName
            }
        }.resultedValues!!.first().toPerson()
        return res
    }

    fun updatePerson(person: Person): Person {
        transaction {
            PersonsTable.update({ PersonsTable.personId eq person.personId }) {
                it[this.firstName] = person.firstName
                it[this.lastName] = person.lastName
            }
        }
        return fetchPerson(person.personId)
    }

    fun removePerson(peronId: PersonId): String{
        transaction {
            GroupContactAssociationTable.deleteWhere { personId eq peronId }
            PhoneNumbersTable.deleteWhere { personId eq peronId }
            EmailsTable.deleteWhere { personId eq peronId }
            AddressesTable.deleteWhere { personId eq peronId }
            GroupContactAssociationTable.deleteWhere { personId eq peronId }
            PersonsTable.deleteWhere { personId eq peronId }

        }
        return "${peronId} is deleted"
    }
    fun fetchPerson(pid: UUID): Person {
        val res = transaction {
            PersonsTable.select(PersonsTable.personId eq pid).map {
                Person(
                    it[PersonsTable.personId],
                    it[PersonsTable.firstName],
                    it[PersonsTable.lastName],
                )
            }
        }.first()
        return res
    }

    fun searchPhoneNumberByPersonName(firstname: String): List<PhoneNumber>{
       val result =  transaction {
            (PersonsTable innerJoin PhoneNumbersTable)
                .select(PersonsTable.firstName eq firstname)
                .map {
                    PhoneNumber(it[PhoneNumbersTable.phoneNumberId], it[PhoneNumbersTable.personId],
                    it[PhoneNumbersTable.phoneNumberType], it[PhoneNumbersTable.phone]) }
        }
        return result
    }

    fun listAllPerson(): List<Person>{
        val list = transaction {
            PersonsTable.selectAll().map {
                row -> Person(row[PersonsTable.personId],row[PersonsTable.firstName],row[PersonsTable.lastName])
            }
        }
        return list
    }

}
