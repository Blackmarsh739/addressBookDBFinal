import com.addressbook.commands.Command
import com.addressbook.storages.AddressDB
import com.example.addressbook.Address
import com.example.addressbook.requests.AddAddressRequest


class AddAddressCommand(
    private val storage: AddressDB,
    private val request: AddAddressRequest
): Command {
    override fun execute(): Address {

        return AddressDB.addAddress(request)
    }
}