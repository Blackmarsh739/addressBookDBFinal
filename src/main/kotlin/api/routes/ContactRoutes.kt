package api.routes

import arrow.core.getOrElse
import com.example.addressbook.requests.AddPersonRequest
import entryPoints.addPersonEntryPoint
import entryPoints.fetchPersonEntryPoint
import entryPoints.listAllPersonsEntryPoint
import entryPoints.removePersonEntryPoint
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import setDatabase
import java.util.UUID

fun Route.personRouting(){

    setDatabase()
    route("/contacts"){
        get {
            val personList = listAllPersonsEntryPoint()
            call.respond(personList)
        }
        get("{id}") {
            val personId = UUID.fromString(call.parameters["id"])
            val person = fetchPersonEntryPoint(personId)
            call.respond(person)
        }
        post {
            val person = call.receive<AddPersonRequest>()
            val addedPerson = addPersonEntryPoint(person).getOrElse { throw Exception("There was an error adding Person") }
            call.respond(addedPerson)
        }
        delete ("{id}"){
            val personId = UUID.fromString(call.parameters["id"])
            val deletedPerson = removePersonEntryPoint(personId)
            call.respond(deletedPerson)
        }
    }
}