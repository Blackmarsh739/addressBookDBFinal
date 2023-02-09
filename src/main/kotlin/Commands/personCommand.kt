import PersonDB.addPerson
import PersonDB.fetchPerson
import PersonDB.listAllPerson
import PersonDB.removePerson
import arrow.core.Either
import com.addressbook.commands.Command
import com.example.addressbook.Person
import com.example.addressbook.PersonId
import com.example.addressbook.PhoneNumber
import com.example.addressbook.requests.AddPersonRequest
import com.example.addressbook.requests.UpdatePersonRequest
import java.util.UUID

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
    override fun execute(): Either<Exception, String> {
        val person = request.toPerson()
        return PersonDB.updatePerson(person)
    }
}

class RemovePersonCommand(private val personId: PersonId) :Command{
    override fun execute(): Either<Exception, String> = removePerson(personId)

}

class FetchPersonCommand(private val pId: UUID): Command{
    override fun execute(): Either<Exception, Person> {
     return fetchPerson(pId)
    }

}

class ListAllPersonCommand(
): Command {
    override fun execute(): Either<Exception, List<Person>> {
        return listAllPerson()
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
