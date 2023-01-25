package com.example.addressbook.requests

import com.example.addressbook.PersonId
import java.util.*

enum class EmailType {
    Home,
    Office
}

data class EmailRequest(
    val personId: PersonId,
    val type: EmailType,
    val emailDetail: String,
)