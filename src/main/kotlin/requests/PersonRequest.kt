package com.example.addressbook.requests

import java.util.UUID

data class AddPersonRequest(
    val firstName: String,
    val lastName: String,
)

data class UpdatePersonRequest(
    val personId: UUID,
    val firstName: String,
    val lastName: String,
)

data class RemovePersonRequest(
    val personId: UUID,
    val firstName: String,
    val lastName: String
)

data class FetchPersonRequest(
    val personId: UUID
)

class ListPersonRequest()