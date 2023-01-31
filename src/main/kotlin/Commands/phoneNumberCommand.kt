package Commands

import com.addressbook.commands.Command
import com.example.addressbook.PhoneNumber
import com.example.addressbook.requests.PhoneNumberRequest
import com.example.addressbook.requests.UpdatePhoneNumberRequest
import storage.PhoneNumberDB

fun UpdatePhoneNumberRequest.toPhoneNumber() =
    PhoneNumber(
        phoneNumberId = this@toPhoneNumber.phoneNumberId,
        phoneNumber = this@toPhoneNumber.phoneNumber,
        personId = this@toPhoneNumber.personId,
        phoneNumberType = this@toPhoneNumber.phoneNumberType
    )
class AddPhoneNumberCommand(
    private val storage: PhoneNumberDB,
    private val request: PhoneNumberRequest
): Command {
    override fun execute(): PhoneNumber {

        return PhoneNumberDB.addPhoneNumber(request)
    }

}

class UpdatePhoneNumberCommand(private val storage: PhoneNumberDB, private val request: UpdatePhoneNumberRequest): Command{
    override fun execute(): PhoneNumber {
        val phoneNumber = request.toPhoneNumber()
        return storage.updatePhoneNumber(phoneNumber)
    }

}

class ListAllPhoneNumberCommand(
    private val storage: PhoneNumberDB,
): Command{
    override fun execute(): List<PhoneNumber> {
        return storage.listAllPhoneNumber()
    }

}
