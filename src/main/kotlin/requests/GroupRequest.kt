package com.example.addressbook.requests

import com.example.addressbook.PersonId

data class GroupRequest (
    val personId: PersonId,
    val groupName: String,
)