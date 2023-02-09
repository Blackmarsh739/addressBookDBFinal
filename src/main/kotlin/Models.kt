package com.example.addressbook
import java.util.UUID

typealias PersonId = UUID

data class Person(
    val personId: PersonId,
    val firstName: String,
    val lastName: String
)


typealias AddressId = UUID

data class Address(
    val addressId: AddressId,
    val personId: PersonId,
    val addressType: String,
    val addressLine: String,
)


typealias PhoneNumberId = UUID
data class PhoneNumber(
    val phoneNumberId: PhoneNumberId,
    val personId: PersonId,
    val phoneNumberType: String,
    val phoneNumber: String,
)

typealias EmailId = UUID
data class Email(
    val emailId: EmailId,
    val personId: PersonId,
    val emailType: String,
    val emailDetail: String
)

typealias GroupId = UUID

data class Group(
    val groupId: GroupId,
    val groupName: String
)