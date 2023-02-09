package com.example.addressbook.requests

import com.example.addressbook.AddressId
import com.example.addressbook.PersonId


data class AddAddressRequest(
    val personId: PersonId,
    val type: String,
    val addressDetail: String,
)
