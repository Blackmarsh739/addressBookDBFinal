package Commands

import arrow.core.Either
import com.example.addressbook.requests.AddGroupRequest
import com.addressbook.commands.Command
import com.example.addressbook.Group
import com.example.addressbook.GroupId
import com.example.addressbook.requests.RemoveGroupRequest
import com.example.addressbook.requests.UpdateGroupRequest
import storage.GroupDB
import storage.GroupDB.addContactsInGroups
import storage.GroupDB.addGroup
import storage.GroupDB.fetchGroup
import storage.GroupDB.listAllGroups
import storage.GroupDB.removeGroup
import storage.GroupDB.updateGroup
import java.util.UUID


fun UpdateGroupRequest.toGroup() =
    Group(
        groupId = this@toGroup.groupId,
        groupName = this@toGroup.groupName,
    )

class AddGroupCommand(
    private val request: AddGroupRequest
): Command {
    override fun execute(): Either<Exception, Group> {
        return addGroup(request)

    }
}

class UpdateGroupCommand(private val request: UpdateGroupRequest):
    Command {
    override fun execute(): Either<Exception, Group> {
        val group = request.toGroup()
        return updateGroup(group)
    }

}

class AddContactInGroupCommand(
    private val gId: UUID,
    private val contactList: List<UUID>
):Command{
    override fun execute(): Either<Exception, List<UUID>> = addContactsInGroups(gId,contactList)
}

class RemoveGroupCommand(private val groupId: GroupId): Command{
    override fun execute(): Either<Exception, String> = removeGroup(groupId)
}

class ListAllGroupCommand(
): Command{
    override fun execute():Either<Exception, List<Group>> {
        return listAllGroups()
    }

}

class FetchGroupCommand(private val gId: UUID): Command{
    override fun execute(): Either<Exception, Group> {
        return fetchGroup(gId)
    }
}