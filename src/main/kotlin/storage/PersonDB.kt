import com.addressbook.tables.Persons
import com.example.addressbook.Person
import com.example.addressbook.PersonId
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object PersonDB {
    fun addPerson(person: Person): Person {
        transaction {
            Persons.insert{
                it[this.personId] = person.personId
                it[this.firstName] = person.firstName
                it[this.lastName] = person.lastName
            }
        }
        return person
    }
    fun updatePerson(person: Person): Person {
        transaction{
            Persons.update({Persons.personId eq person.personId}) {
                it[this.firstName] = person.firstName
                it[this.lastName] = person.lastName
            }
        }
        return person
    }
}