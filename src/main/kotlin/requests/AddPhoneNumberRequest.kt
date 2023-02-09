package com.example.addressbook.requests

import com.example.addressbook.PersonId
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonSerialize
enum class PhoneNumberType {
    Home,
    Office
}

data class AddPhoneNumberRequest(
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