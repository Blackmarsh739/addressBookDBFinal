package storage

import arrow.core.Either
import com.addressbook.tables.PhoneNumbersTable
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
    fun addPhoneNumber(phoneNumber: PhoneNumberRequest): Either<Exception, PhoneNumber> {
        return try {
            val res = transaction {
                PhoneNumbersTable.insert {
                    it[this.personId] = phoneNumber.personId
                    it[this.phoneNumberType] = phoneNumber.type
                    it[this.phone] = phoneNumber.phoneNumber
                }
            }.resultedValues!!.first().toPhone()
            Either.Right(res)
        } catch (e: Exception) {
            Either.Left(Exception("There was some error."))
        }
    }

    fun updatePhoneNumber(phoneNumber: PhoneNumber): Either<Exception, PhoneNumber> {
       return try {
            transaction {
                PhoneNumbersTable.update({ PhoneNumbersTable.phoneNumberId eq phoneNumber.phoneNumberId }) {
                    it[this.phone] = phoneNumber.phoneNumber
                }
            }
            Either.Right(phoneNumber)
        } catch (e: Exception) {
            Either.Left(Exception("There was some error."))
        }
    }

    fun listAllPhoneNumber(): Either<Exception ,List<PhoneNumber>>{
       return try {
            val list = transaction {
                PhoneNumbersTable.selectAll().map { row ->
                    PhoneNumber(
                        row[PhoneNumbersTable.personId],
                        row[PhoneNumbersTable.phoneNumberId],
                        row[PhoneNumbersTable.phoneNumberType],
                        row[PhoneNumbersTable.phone]
                    )
                }
            }
            Either.Right(list)
        }
        catch (e: Exception) {
            Either.Left(Exception("There was some error."))
        }
    }
}