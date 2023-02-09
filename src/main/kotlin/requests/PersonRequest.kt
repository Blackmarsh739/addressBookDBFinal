package com.example.addressbook.requests

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.util.UUID

@JsonSerialize
data class AddPersonRequest(
    val firstName: String,
    val lastName: String,
)

data class UpdatePersonRequest(
    val personId: UUID,
    val firstName: String,
    val lastName: String,
)

data class FetchPersonRequest(
    val personId: UUID
)

class ListPersonRequest()