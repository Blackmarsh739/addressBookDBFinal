package com.example.addressbook.requests

import com.example.addressbook.AddressId
import com.example.addressbook.PersonId


enum class AddressType {
    Home,
    Office
}

data class AddAddressRequest(
    val personId: PersonId,
    val type: AddressType,
    val addressDetail: String,
)
