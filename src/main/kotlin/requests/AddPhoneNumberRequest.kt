package com.example.addressbook.requests

import com.example.addressbook.PersonId
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.*

@JsonSerialize

data class AddPhoneNumberRequest(
    val personId: PersonId,
    val type: String,
    val phoneNumber: String,
)

data class UpdatePhoneNumberRequest (
    val phoneNumberId: UUID,
    val phoneNumber: String,
    val personId: PersonId,
    val phoneNumberType: String
)