package com.example.addressbook.requests

import com.example.addressbook.PersonId
import com.example.addressbook.PhoneNumber
import com.example.addressbook.PhoneNumberId
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

data class UpdatePhoneNumberRequest (
    val phoneNumberId: UUID,
    val phoneNumber: String,
    val personId: PersonId,
    val phoneNumberType: PhoneNumberType
)