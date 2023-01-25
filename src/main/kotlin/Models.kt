package com.example.addressbook

import com.example.addressbook.requests.AddressType
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
    val addressType: AddressType,
    val addressLine: String,
)