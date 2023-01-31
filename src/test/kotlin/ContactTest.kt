import Commands.*
import com.addressbook.storages.AddressDB
import com.example.addressbook.requests.*
import org.junit.jupiter.api.Test
import storage.EmailDB
import storage.GroupDB
import storage.PhoneNumberDB

class ContactTest: AppTest() {
    @Test
    fun `test add contact name`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(PersonDB, person1).execute()
    }
    @Test
    fun `test delete contact`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(PersonDB, person1).execute()

        RemovePersonCommand(PersonDB, parth.personId).execute()
    }

    @Test
    fun `test update contact name` (){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(PersonDB, person1).execute()
        UpdatePersonCommand(PersonDB, UpdatePersonRequest(parth.personId,"Black","Marsh")).execute()

    }

    @Test
    fun `test add email`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(PersonDB, person1).execute()
        AddEmailCommand(EmailDB, EmailRequest(parth.personId, EmailType.Office, "parth.raval@vyana.com")).execute()

    }
    @Test
    fun `test add phone number`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(PersonDB, person1).execute()
        val p1 = AddPhoneNumberCommand(PhoneNumberDB, PhoneNumberRequest(parth.personId, PhoneNumberType.Home,"123")).execute()

    }

    @Test
    fun `test update phone number`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(PersonDB, person1).execute()
        val p1 = AddPhoneNumberCommand(PhoneNumberDB, PhoneNumberRequest(parth.personId, PhoneNumberType.Home,"123")).execute()
        UpdatePhoneNumberCommand(PhoneNumberDB, UpdatePhoneNumberRequest((p1.phoneNumberId), "007", parth.personId, p1.phoneNumberType)).execute()

    }

    @Test
    fun `test add address`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(PersonDB, person1).execute()
        AddAddressCommand(AddressDB, AddAddressRequest(parth.personId,AddressType.Home,"Bhavnagar")).execute()

    }

    @Test
    fun `test creat group`(){
        val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute())

    }

    @Test
    fun `test update group`(){
        val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute())
        UpdateGroupCommand(GroupDB, UpdateGroupRequest(g1.groupId, "Vayana Network")).execute()

    }

    @Test
    fun `test delete group`(){
        val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute())
        RemoveGroupCommand(GroupDB, g1.groupId ).execute()

    }

    @Test
    fun `test add contact in group`(){
        val person1 = AddPersonRequest("Parth","Raval")
        val parth = AddPersonCommand(PersonDB, person1).execute()
        val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute())
        AddContactInGroupCommand(g1.groupId, listOf(parth.personId)).execute()

    }
}