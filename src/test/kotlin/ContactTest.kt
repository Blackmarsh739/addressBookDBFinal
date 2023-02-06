import Commands.*
import com.addressbook.storages.AddressDB
import com.example.addressbook.requests.*
import org.junit.jupiter.api.Test
import storage.EmailDB
import storage.GroupDB
import storage.PhoneNumberDB

class ContactTest: AppTest() {
    val dv = connectToDatabase()
    val rst = resetDatabase()
    @Test
    fun `test add contact name`(){
        val person1 = AddPersonRequest("Parth","Raval")
        AddPersonCommand(person1).execute()
    }
    @Test
    fun `test delete contact`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(person1).execute()!!.orNull()

        if (parth != null) {
            RemovePersonCommand(parth.personId).execute()
        }
    }

    @Test
    fun `test update contact name` (){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(person1).execute().orNull()!!
        UpdatePersonCommand(UpdatePersonRequest(parth.personId,"Black","Marsh")).execute()

    }

    @Test
    fun `test add email`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(person1).execute().orNull()!!
        AddEmailCommand(EmailRequest(parth.personId, EmailType.Office, "parth.raval@vyana.com")).execute()

    }
    @Test
    fun `test add phone number`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(person1).execute().orNull()!!
        val p1 = AddPhoneNumberCommand(PhoneNumberRequest(parth.personId, PhoneNumberType.Home,"123")).execute()

    }

    @Test
    fun `test update phone number`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(person1).execute().orNull()!!
        val p1 = AddPhoneNumberCommand(PhoneNumberRequest(parth.personId, PhoneNumberType.Home,"123")).execute().orNull()!!
        UpdatePhoneNumberCommand(UpdatePhoneNumberRequest((p1.phoneNumberId), "007", parth.personId, p1.phoneNumberType)).execute()

    }

    @Test
    fun `test add address`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(person1).execute().orNull()!!
        AddAddressCommand(AddAddressRequest(parth.personId,AddressType.Home,"Bhavnagar")).execute()

    }

    @Test
    fun `test creat group`(){
        val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute())

    }

    @Test
    fun `test update group`(){
        val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute()).orNull()!!
        UpdateGroupCommand(UpdateGroupRequest(g1.groupId, "Vayana Network")).execute()

    }

    @Test
    fun `test delete group`(){
        val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute()).orNull()!!
        RemoveGroupCommand(g1.groupId ).execute()

    }

    @Test
    fun `test add contact in group`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(person1).execute().orNull()!!
        val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute()).orNull()!!
        AddContactInGroupCommand(g1.groupId, listOf(parth.personId)).execute()

    }
}