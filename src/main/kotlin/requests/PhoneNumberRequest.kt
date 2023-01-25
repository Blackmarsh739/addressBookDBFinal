package com.example.addressbook.requests

import com.example.addressbook.PersonId
import java.util.*

enum class PhoneNumberType {
    Home,
    Office
}

data class PhoneNumberRequest(
    val personId: PersonId,
    val type: PhoneNumberType,
    val phoneNumber: String,
)