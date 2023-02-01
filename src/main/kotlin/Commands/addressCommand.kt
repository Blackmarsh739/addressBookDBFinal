import arrow.core.Either
import com.addressbook.commands.Command
import com.addressbook.storages.AddressDB
import com.example.addressbook.Address
import com.example.addressbook.requests.AddAddressRequest


class AddAddressCommand(
    private val request: AddAddressRequest
): Command {
    override fun execute(): Either<Exception, Address> {

        return AddressDB.addAddress(request)
    }
}