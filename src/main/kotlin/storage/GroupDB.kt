package storage

import com.addressbook.tables.GroupContactAssociationTable
import com.addressbook.tables.GroupsTable
import com.example.addressbook.Group
import com.example.addressbook.GroupId
import com.example.addressbook.requests.AddGroupRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

fun ResultRow.toGroup() = Group(
    groupName = this@toGroup[GroupsTable.groupName],
    groupId = this@toGroup[GroupsTable.groupId]
)
object GroupDB {
    fun addGroup(group: AddGroupRequest): Group{
       val res = transaction {
            GroupsTable.insert {
                it[this.groupName]= group.groupName
            }
        }.resultedValues!!.first().toGroup()
        return res
    }

    fun updateGroup(group: Group): Group {
        transaction {
            GroupsTable.update ({GroupsTable.groupId eq group.groupId }) {
                it[this.groupName] = group.groupName
            }
        }
        return group
    }

    fun addContactsInGroups(gId: UUID, contactList: List<UUID>){

          val res =   transaction {
                contactList.forEach {cid->
                GroupContactAssociationTable.insert {
                    it[this.groupId] = gId
                    it[this.personId] = cid
                }
            }
        }
    }

    fun removeGroup(groupId: GroupId): String {
        transaction {
            GroupContactAssociationTable.deleteWhere { GroupContactAssociationTable.groupId eq groupId }
            GroupsTable.deleteWhere { GroupsTable.groupId eq groupId }
        }
        return "${groupId} is deleted"
    }

    fun listAllGroups(): List<Group>{
        val list = transaction {
            GroupsTable.selectAll().map {
                    row -> Group(row[GroupsTable.groupId],row[GroupsTable.groupName])

            }
        }
        return list
    }
}