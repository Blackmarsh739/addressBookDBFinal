package Commands

import com.example.addressbook.requests.AddGroupRequest
import com.addressbook.commands.Command
import com.example.addressbook.Group
import com.example.addressbook.GroupId
import com.example.addressbook.requests.RemoveGroupRequest
import com.example.addressbook.requests.UpdateGroupRequest
import storage.GroupDB
import storage.GroupDB.addContactsInGroups
import java.util.UUID


fun UpdateGroupRequest.toGroup() =
    Group(
        groupId = this@toGroup.groupId,
        groupName = this@toGroup.groupName,
    )

class AddGroupCommand(
    private val request: AddGroupRequest
): Command {
    override fun execute(): Group {
        return GroupDB.addGroup(request)

    }
}

class UpdateGroupCommand(private val storage: GroupDB, private val request: UpdateGroupRequest): Command {
    override fun execute(): Group {
        val group = request.toGroup()
        return storage.updateGroup(group)
    }

}

class AddContactInGroupCommand(
    private val gId: UUID,
    private val contactList: List<UUID>
):Command{
    override fun execute() {
        addContactsInGroups(gId,contactList)
    }
}

class RemoveGroupCommand(private val storage: GroupDB, private val groupId: GroupId): Command{
    override fun execute(): Any {
        return storage.removeGroup(groupId)
    }

}

class ListAllGroupCommand(
    private val storage: GroupDB,
): Command{
    override fun execute(): List<Group> {
        return storage.listAllGroups()
    }

}