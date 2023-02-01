package entryPoints

import AddAddressCommand
import AddPersonCommand
import Commands.AddEmailCommand
import Commands.AddGroupCommand
import Commands.AddPhoneNumberCommand
import Commands.UpdatePhoneNumberCommand
import RemovePersonCommand
import UpdatePersonCommand
import arrow.core.Either
import com.example.addressbook.*
import com.example.addressbook.requests.*
import handler.Handlers.addAddressHandler
import handler.Handlers.addEmailHandler
import handler.Handlers.addGroupHandler
import handler.Handlers.addPersonHandler
import handler.Handlers.addPhoneNumberHandler
import handler.Handlers.removePersonHandler
import handler.Handlers.updatePersonHandler
import handler.Handlers.updatePhoneNumberHandler

//Person
fun addPersonEntryPoint(person: AddPersonRequest): Either<Exception, Person>{
    val cmd = AddPersonCommand(person)
    return addPersonHandler(cmd)
}
fun updatePersonEntryPoint(person: UpdatePersonRequest): Either<Exception, Person>{
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
fun addPhoneNumberEntryPoint(phoneNumber: PhoneNumberRequest): Either<Exception, PhoneNumber>{
    val cmd = AddPhoneNumberCommand(phoneNumber)
    return addPhoneNumberHandler(cmd)
}

fun updatePhoneNumberEntryPoint(phoneNumber: UpdatePhoneNumberRequest): Either<Exception, PhoneNumber>{
    val cmd = UpdatePhoneNumberCommand(phoneNumber)
    return updatePhoneNumberHandler(cmd)
}

fun addGroupEntryPoint(group: AddGroupRequest): Either<Exception, Group>{
    val cmd = AddGroupCommand(group)
    return addGroupHandler(cmd)
}