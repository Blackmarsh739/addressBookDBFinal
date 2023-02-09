package storage

import arrow.core.Either
import com.addressbook.tables.GroupContactAssociationTable
import com.addressbook.tables.GroupsTable
import com.example.addressbook.Group
import com.example.addressbook.GroupId
import com.example.addressbook.Person
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
    fun addGroup(group: AddGroupRequest): Either<Exception, Group> {
        return try {
            val res = transaction {
                GroupsTable.insert {
                    it[this.groupName] = group.groupName
                }
            }.resultedValues!!.first().toGroup()
            Either.Right(res)
        } catch (e: Exception) {
            Either.Left(Exception("There was some error."))
        }
    }

    fun updateGroup(group: Group): Either<Exception, Group> {
        return try {
            transaction {
                GroupsTable.update({ GroupsTable.groupId eq group.groupId }) {
                    it[this.groupName] = group.groupName
                }
            }
            Either.Right(group)
        } catch (e: Exception) {
            Either.Left(Exception("There was some error."))
        }
    }

    fun addContactsInGroups(gId: UUID, contactList: List<UUID>): Either<Exception, List<UUID>> {

      return try {
           val res = transaction {
               contactList.forEach { cid ->
                   GroupContactAssociationTable.insert {
                       it[this.groupId] = gId
                       it[this.personId] = cid
                   }
               }
           }
          Either.Right(contactList)
       } catch (e: Exception) {
           Either.Left(Exception("There was some error."))
       }
    }

    fun removeGroup(groupId: GroupId): Either<Exception, String> {
        return try {
            transaction {
                GroupContactAssociationTable.deleteWhere { GroupContactAssociationTable.groupId eq groupId }
                GroupsTable.deleteWhere { GroupsTable.groupId eq groupId }
            }
            Either.Right("Group was deleted")
        } catch (e: Exception) {
            Either.Left(Exception("There was some error."))
        }

    }

    fun listAllGroups(): Either<Exception, List<Group>> {
       return try {
            val list = transaction {

                GroupsTable.selectAll().map { row ->
                    Group(row[GroupsTable.groupId], row[GroupsTable.groupName])
                }
            }
            Either.Right(list)
        } catch (e: Exception) {
            Either.Left(Exception("There was some error."))
        }
    }

    fun fetchGroup(gId: UUID): Either<Exception, Group> {
        return try {
            val res = transaction {
                GroupsTable.select { GroupsTable.groupId eq gId }.map {
                    Group(
                        it[GroupsTable.groupId],
                        it[GroupsTable.groupName],
                    )
                }
            }.first()
            Either.Right(res)
        } catch (e: Exception) {
            Either.Left(Exception("There was some error."))
        }
    }
}