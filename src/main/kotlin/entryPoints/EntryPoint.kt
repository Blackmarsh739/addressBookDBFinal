package entryPoints

import AddAddressCommand
import AddPersonCommand
import Commands.*
import FetchPersonCommand
import ListAllPersonCommand
import RemovePersonCommand
import UpdatePersonCommand
import arrow.core.Either
import com.example.addressbook.*
import com.example.addressbook.requests.*
import handler.Handlers.addAddressHandler
import handler.Handlers.addContactInGroupHandler
import handler.Handlers.addEmailHandler
import handler.Handlers.addGroupHandler
import handler.Handlers.addPersonHandler
import handler.Handlers.addPhoneNumberHandler
import handler.Handlers.fetchPersonHandler
import handler.Handlers.listAllPersonHandler
import handler.Handlers.listAllPhoneNumberHandler
import handler.Handlers.removeGroupHandler
import handler.Handlers.removePersonHandler
import handler.Handlers.updateGroupHandler
import handler.Handlers.updatePersonHandler
import handler.Handlers.updatePhoneNumberHandler
import java.util.UUID

//Person
fun addPersonEntryPoint(person: AddPersonRequest): Either<Exception, Person>{
    val cmd = AddPersonCommand(person)
    return addPersonHandler(cmd)
}
fun updatePersonEntryPoint(person: UpdatePersonRequest): Either<Exception, String>{
    val cmd = UpdatePersonCommand(person)
    return updatePersonHandler(cmd)
}
fun removePersonEntryPoint(person: PersonId): Either<Exception, String>{
    val cmd = RemovePersonCommand(person)
    return removePersonHandler(cmd)
}

//Address
fun addAddressEntryPoint(address: AddAddressRequest): Either<Exception, Address>{
    val cmd = AddAddressCommand(address)
    return addAddressHandler(cmd)
}

//Email
fun addEmailEntryPoint(email: EmailRequest): Either<Exception, Email>{
    val cmd = AddEmailCommand(email)
    return addEmailHandler(cmd)
}

//phoneNumber
fun addPhoneNumberEntryPoint(phoneNumber: AddPhoneNumberRequest): Either<Exception, PhoneNumber>{
    val cmd = AddPhoneNumberCommand(phoneNumber)
    return addPhoneNumberHandler(cmd)
}

fun updatePhoneNumberEntryPoint(phoneNumber: UpdatePhoneNumberRequest): Either<Exception, PhoneNumber>{
    val cmd = UpdatePhoneNumberCommand(phoneNumber)
    return updatePhoneNumberHandler(cmd)
}

fun listAllPhoneNumberEntryPoint(): Either<Exception, List<PhoneNumber>>{
    val cmd = ListAllPhoneNumberCommand()
    return listAllPhoneNumberHandler(cmd)
}

fun addGroupEntryPoint(group: AddGroupRequest): Either<Exception, Group>{
    val cmd = AddGroupCommand(group)
    return addGroupHandler(cmd)
}

fun updateGroupEntryPoint(group: UpdateGroupRequest): Either<Exception, Group>{
    val cmd = UpdateGroupCommand(group)
    return updateGroupHandler(cmd)
}

fun removeGroupEntryPoint(group: GroupId): Either<Exception, String>{
    val cmd = RemoveGroupCommand(group)
    return removeGroupHandler(cmd)
}

fun addContactInGroupEntryPoint(group: GroupId , contactList: List<UUID>): Either<Exception, List<UUID>>{
    val cmd = AddContactInGroupCommand(group, contactList)
    return addContactInGroupHandler(cmd)
}

fun listAllPersonsEntryPoint():Either<Exception, List<Person>>{
    val cmd = ListAllPersonCommand()
    return listAllPersonHandler(cmd)
}

fun fetchPersonEntryPoint(pId: UUID): Either<Exception, Person> {
    val cmd = FetchPersonCommand(pId)
    return fetchPersonHandler(cmd)
}