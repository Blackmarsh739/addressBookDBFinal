package storage

import com.addressbook.tables.PhoneNumbers
import com.example.addressbook.PhoneNumber
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object PhoneDB {
    fun addPhoneNumber(phoneNumber: PhoneNumber): PhoneNumber{
        transaction {
            PhoneNumbers.insert{
                it[this.phoneNumberId] =phoneNumber.phoneNumberId
                it[this.personId] = phoneNumber.personId
                it[this.phoneNumberType] = phoneNumber.phoneNumberType
                it[this.phone] = phoneNumber.phoneNumber
            }
        }

        return phoneNumber
    }
}