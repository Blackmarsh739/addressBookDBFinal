package storage

import com.addressbook.tables.PersonsTable
import com.addressbook.tables.PhoneNumbersTable
import com.example.addressbook.Person
import com.example.addressbook.PhoneNumber
import com.example.addressbook.requests.PhoneNumberRequest
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun ResultRow.toPhone() = PhoneNumber(
    personId = this@toPhone[PhoneNumbersTable.personId],
    phoneNumber = this@toPhone[PhoneNumbersTable.phone],
    phoneNumberId = this@toPhone[PhoneNumbersTable.phoneNumberId],
    phoneNumberType = this@toPhone[PhoneNumbersTable.phoneNumberType]
)
object PhoneNumberDB {
    fun addPhoneNumber(phoneRequest: PhoneNumberRequest): PhoneNumber{
        val res = transaction {
            PhoneNumbersTable.insert{
                it[this.personId] = phoneRequest.personId
                it[this.phoneNumberType] = phoneRequest.type
                it[this.phone] = phoneRequest.phoneNumber
            }
        }.resultedValues!!.first().toPhone()

        return res
    }

    fun updatePhoneNumber(phoneNumber: PhoneNumber): PhoneNumber{
        transaction {
            PhoneNumbersTable.update({PhoneNumbersTable.phoneNumberId eq phoneNumber.phoneNumberId}) {
                it[this.phone] = phoneNumber.phoneNumber
            }
        }
        return phoneNumber
    }

    fun listAllPhoneNumber(): List<PhoneNumber>{
        val list = transaction {
            PhoneNumbersTable.selectAll().map {
                    row -> PhoneNumber(row[PhoneNumbersTable.personId],row[PhoneNumbersTable.phoneNumberId],row[PhoneNumbersTable.phoneNumberType], row[PhoneNumbersTable.phone])
            }
        }
        return list
    }
}