package api.routes

import arrow.core.getOrElse
import com.example.addressbook.requests.AddGroupRequest
import com.example.addressbook.requests.UpdateGroupRequest
import entryPoints.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import setDatabase
import java.util.*

fun Route.groupRouting(){

    setDatabase()
    route("/groups"){
        get {
            val groupList = listAllGroupEntryPoint()
            call.respond(groupList)
        }
        get  ("{id}") {
            val groupId = UUID.fromString(call.parameters["id"])
            val group = fetchGroupEntryPoint(groupId)
            call.respond(group)
        }
        post{
            val group = call.receive<AddGroupRequest>()
            val addedGroup = addGroupEntryPoint(group).getOrElse { throw Exception("There was an error adding Group") }
            call.respond(addedGroup)
        }
        delete ("{id}"){
            val groupId = UUID.fromString(call.parameters["id"])
            val deletedGroup = removeGroupEntryPoint(groupId)
            call.respond(deletedGroup)
        }
        put ("{id}"){
            val groupId = UUID.fromString(call.parameters["id"])
            val groupRequest = call.receive<UpdateGroupRequest>()
            val updatedGroup = updateGroupEntryPoint(groupRequest)
            call.respond(updatedGroup)

        }
    }
}