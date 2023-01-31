import com.addressbook.commands.Command
import com.example.addressbook.Person
import com.example.addressbook.PersonId
import com.example.addressbook.PhoneNumber
import com.example.addressbook.requests.AddPersonRequest
import com.example.addressbook.requests.UpdatePersonRequest

fun UpdatePersonRequest.toPerson() =
    Person(
        personId = this@toPerson.personId,
        firstName = this@toPerson.firstName,
        lastName = this@toPerson.lastName
    )

class AddPersonCommand(
    private val storage: PersonDB,
    private val request: AddPersonRequest
): Command {
    override fun execute(): Person {
        return storage.addPerson(request)
    }
}
class UpdatePersonCommand(private val storage: PersonDB, private val request: UpdatePersonRequest) : Command {
    override fun execute(): Person {
        val person = request.toPerson()
        return storage.updatePerson(person)
    }
}

class RemovePersonCommand(private val storage: PersonDB, private val personId: PersonId) :Command{
    override fun execute(): Any {
        return storage.removePerson(personId)
    }

}

class ListAllPersonCommand(
    private val storage: PersonDB,
): Command {
    override fun execute(): List<Person> {
        return storage.listAllPerson()
    }

}

class SerchContactCommand(
    private val storage: PersonDB,
    private val firstName: String
): Command{
    override fun execute(): Any {
        return storage.searchPhoneNumberByPersonName(firstName)
    }

}
