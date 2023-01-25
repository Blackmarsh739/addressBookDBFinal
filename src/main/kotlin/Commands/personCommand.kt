import com.addressbook.commands.Command
import com.example.addressbook.Person
import com.example.addressbook.PersonId
import com.example.addressbook.requests.AddPersonRequest
import com.example.addressbook.requests.RemovePersonRequest
import com.example.addressbook.requests.UpdatePersonRequest
import java.util.*


fun AddPersonRequest.toPerson() =
    Person(
        personId = UUID.randomUUID(),
        firstName = this@toPerson.firstName,
        lastName = this@toPerson.lastName
    )


fun UpdatePersonRequest.toPerson() =
    Person(
        personId = this@toPerson.personId,
        firstName = this@toPerson.firstName,
        lastName = this@toPerson.lastName
    )



fun RemovePersonRequest.toPerson() = Person(
    personId = this@toPerson.personId,
    firstName = this@toPerson.firstName,
    lastName = this@toPerson.lastName,
)


class AddPersonCommand(
    private val storage: PersonDB,
    private val request: AddPersonRequest
): Command {
    override fun execute(): Person {
        val person = request.toPerson()


        val personDetail = storage.addPerson(person)

        return Person(
            personId = personDetail.personId,
            firstName = personDetail.firstName,
            lastName = personDetail.lastName,
        )
    }
}


class UpdatePersonCommand(private val storage: PersonDB, private val request: UpdatePersonRequest) : Command {
    override fun execute(): Person {
        val person = request.toPerson()
        val personDetail = storage.updatePerson(person)
        return Person(
            personId = personDetail.personId,
            firstName = personDetail.firstName,
            lastName = personDetail.lastName,
        )
    }
}

//class FetchPersonCommand(
//    private val storage: PersonDB,
//    private val personId: PersonId,
//) : Command {
//    override fun execute(): Person {
//        val person = storage.fetchPerson(storage, personId)
//
//        return Person(
//            personId = person.personId,
//            firstName = person.firstName,
//            lastName = person.lastName,
//        )
//    }
//}


//class RemovePersonCommand(
//    private val storage: PersonDatabaseStorage,
//    private val personId: PersonId,
//) : Command {
//    override fun execute(): Any {
//        PersonPhoneNumberRepo.removeAllPhoneNumberByPersonId(personId)
//        val phoneNumberIdsTobeRemoved = PersonPhoneNumberRepo.getAllPhoneNumberIdsByPersonId(personId)
//        phoneNumberIdsTobeRemoved.forEach {
//            PhoneNumberRepo.removePhoneNumber(it)
//        }
//
//
//        PersonAddressRepo.removeAllAddressByPersonId(personId)
//        val addressIdsTobeRemoved = PersonAddressRepo.getAllAddressIdsByPersonId(personId)
//        addressIdsTobeRemoved.forEach {
//            AddressRepo.removeAddress(it)
//        }
//
//        PersonEmailRepo.removeAllEmailByPersonId(personId)
//        val emailIdsTobeRemoved = PersonEmailRepo.getAllEmailIdsByPersonId(personId)
//        emailIdsTobeRemoved.forEach {
//            EmailRepo.removeEmail(it)
//        }
//
//        PersonGroupRepo.removeAllGroupByPersonId(personId)
//        val groupIdsTobeRemoved = PersonGroupRepo.getAllGroupIdsByPersonId(personId)
//        groupIdsTobeRemoved.forEach {
//            GroupRepo.removeGroup(it)
//        }
//        val personDetail = PersonRepo.removePerson(storage, personId)
//        return " contact deleted"
//    }
//}
//
//class ListPersonCommand(
//    private val storage: PersonDatabaseStorage,
//    ): Command {
//    override fun execute(): List<Person> {
//        return PersonRepo.listPersons(storage)
//    }
//}
//
//
//
//