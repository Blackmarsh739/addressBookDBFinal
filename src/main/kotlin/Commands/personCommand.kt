import PersonDB.addPerson
import PersonDB.removePerson
import arrow.core.Either
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
    private val request: AddPersonRequest
): Command {
    override fun execute(): Either<Exception, Person> {
        return addPerson(request)
    }
}
class UpdatePersonCommand(private val request: UpdatePersonRequest) : Command {
    override fun execute(): Either<Exception, Person> {
        val person = request.toPerson()
        return PersonDB.updatePerson(person)
    }
}

class RemovePersonCommand(private val personId: PersonId) :Command{
    override fun execute(): Either<Exception, String> = removePerson(personId)

}

class ListAllPersonCommand(
    private val storage: PersonDB,
): Command {
    override fun execute(): Either<Exception, List<Person>> {
        return storage.listAllPerson()
    }

}

class SerchContactCommand(
    private val storage: PersonDB,
    private val firstName: String
): Command{
    override fun execute(): Either<Exception, List<PhoneNumber>> {
        return storage.searchPhoneNumberByPersonName(firstName)
    }

}
