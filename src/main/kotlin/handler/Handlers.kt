package handler

import AddAddressCommand
import AddPersonCommand
import Commands.AddEmailCommand
import Commands.AddGroupCommand
import Commands.AddPhoneNumberCommand
import Commands.UpdatePhoneNumberCommand
import PersonDB
import RemovePersonCommand
import UpdatePersonCommand
import arrow.core.Either
import com.example.addressbook.*
import com.example.addressbook.requests.EmailRequest
import com.example.addressbook.requests.PhoneNumberRequest

object Handlers {
    fun addPersonHandler(cmd: AddPersonCommand): Either<Exception, Person> = cmd.execute()

    fun updatePersonHandler(cmd: UpdatePersonCommand): Either<Exception, Person> = cmd.execute()

    fun removePersonHandler(cmd: RemovePersonCommand): Either<Exception, String> = cmd.execute()

    fun addAddressHandler(cmd: AddAddressCommand): Either<Exception, Address> = cmd.execute()

    fun addEmailHandler(cmd: AddEmailCommand): Either<Exception, Email> = cmd.execute()

    fun addPhoneNumberHandler(cmd: AddPhoneNumberCommand): Either<Exception, PhoneNumber> = cmd.execute()
    fun updatePhoneNumberHandler(cmd: UpdatePhoneNumberCommand): Either<Exception, PhoneNumber> = cmd.execute()

    fun addGroupHandler(cmd: AddGroupCommand): Either<Exception, Group> = cmd.execute()
}