package Commands

import arrow.core.Either
import com.addressbook.commands.Command
import com.example.addressbook.PhoneNumber
import com.example.addressbook.PhoneNumberId
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
    private val request: PhoneNumberRequest
): Command {
    override fun execute(): Either<Exception, PhoneNumber> = PhoneNumberDB.addPhoneNumber(request)


}

class UpdatePhoneNumberCommand(
    private val request: UpdatePhoneNumberRequest
): Command{
    override fun execute(): Either<Exception, PhoneNumber> = PhoneNumberDB.updatePhoneNumber(request.toPhoneNumber())

}

class ListAllPhoneNumberCommand(
    private val storage: PhoneNumberDB,
): Command{
    override fun execute(): Either<Exception ,List<PhoneNumber>>{
        return storage.listAllPhoneNumber()
    }

}
