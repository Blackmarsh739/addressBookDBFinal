import com.addressbook.commands.Command
import com.addressbook.storages.AddressDB
import com.example.addressbook.Address
import com.example.addressbook.Person
import com.example.addressbook.requests.AddAddressRequest
import java.util.*

fun AddAddressRequest.toAddress() =
    Address(
        addressId = UUID.randomUUID(),
        personId = this@toAddress.personId,
        addressType = this@toAddress.type,
        addressLine = this@toAddress.addressDetail
    )

class AddAddressCommand(
    private val storage: AddressDB,
    private val request: AddAddressRequest
): Command {
    override fun execute(): Address {
        val address = request.toAddress()


        val addressDetail = AddressDB.addAddress(address)

        return Address(
            addressId= addressDetail.addressId,
            personId = addressDetail.personId,
            addressType = addressDetail.addressType,
            addressLine=addressDetail.addressLine
        )
    }
}