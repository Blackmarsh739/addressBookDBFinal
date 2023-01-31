package com.example.addressbook.requests

import com.example.addressbook.PersonId
import java.util.UUID
data class AddGroupRequest (
    val groupName: String,
)
data class UpdateGroupRequest (
    val groupId: UUID,
    val groupName: String,
)
data class RemoveGroupRequest (
    val groupId: UUID
)