package handler

import AddAddressCommand
import AddPersonCommand
import Commands.*
import FetchPersonCommand
import ListAllPersonCommand
import RemovePersonCommand
import UpdatePersonCommand
import arrow.core.Either
import com.example.addressbook.*
import java.util.UUID

object Handlers {
    fun addPersonHandler(cmd: AddPersonCommand): Either<Exception, Person> = cmd.execute()

    fun updatePersonHandler(cmd: UpdatePersonCommand): Either<Exception, String> = cmd.execute()

    fun removePersonHandler(cmd: RemovePersonCommand): Either<Exception, String> = cmd.execute()

    fun addAddressHandler(cmd: AddAddressCommand): Either<Exception, Address> = cmd.execute()

    fun addEmailHandler(cmd: AddEmailCommand): Either<Exception, Email> = cmd.execute()

    fun addPhoneNumberHandler(cmd: AddPhoneNumberCommand): Either<Exception, PhoneNumber> = cmd.execute()
    fun updatePhoneNumberHandler(cmd: UpdatePhoneNumberCommand): Either<Exception, PhoneNumber> = cmd.execute()
    fun listAllPhoneNumberHandler(cmd: ListAllPhoneNumberCommand): Either<Exception, List<PhoneNumber>> = cmd.execute()

    fun addGroupHandler(cmd: AddGroupCommand): Either<Exception, Group> = cmd.execute()

    fun updateGroupHandler(cmd: UpdateGroupCommand): Either<Exception, Group> = cmd.execute()

    fun removeGroupHandler(cmd: RemoveGroupCommand): Either<Exception, String> = cmd.execute()
    fun addContactInGroupHandler(cmd: AddContactInGroupCommand): Either<Exception, List<UUID>> = cmd.execute()
    fun listAllPersonHandler(cmd: ListAllPersonCommand): Either<Exception, List<Person>> = cmd.execute()
    fun fetchPersonHandler(cmd: FetchPersonCommand): Either<Exception, Person> = cmd.execute()
}