package Commands

import com.addressbook.commands.Command
import com.addressbook.tables.PhoneNumbers
import com.example.addressbook.PersonId
import com.example.addressbook.PhoneNumber
import com.example.addressbook.requests.PhoneNumberRequest
import storage.PhoneDB
import java.util.*

fun PhoneNumberRequest.toPhoneNumber() =
    PhoneNumber(
        phoneNumberId = UUID.randomUUID(),
        personId= this@toPhoneNumber.personId,
        phoneNumberType = this@toPhoneNumber.type,
        phoneNumber = this@toPhoneNumber.phoneNumber
    )

class AddPhoneNumberCommand(
    private val storage: PhoneDB,
    private val request: PhoneNumberRequest
): Command {
    override fun execute(): Any {
        val phoneNumber = request.toPhoneNumber()

        val phoneNumberDetail = PhoneDB.addPhoneNumber(phoneNumber)

        return PhoneNumber(
            phoneNumberId = phoneNumberDetail.phoneNumberId,
            personId= phoneNumberDetail.personId,
            phoneNumberType = phoneNumberDetail.phoneNumberType,
            phoneNumber= phoneNumberDetail.phoneNumber,



        )
    }

}
