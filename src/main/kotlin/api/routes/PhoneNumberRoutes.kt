package api.routes

import arrow.core.getOrElse
import com.example.addressbook.requests.AddPhoneNumberRequest
import entryPoints.addPhoneNumberEntryPoint
import entryPoints.listAllPhoneNumberEntryPoint
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import setDatabase

fun Route.phoneNumberRouting(){

    setDatabase()
    route("/phones"){
        get {
            val phoneNumberList = listAllPhoneNumberEntryPoint()
            call.respond(phoneNumberList)
        }
        post {
            val phoneNumber = call.receive<AddPhoneNumberRequest>()
            val addedPhoneNumber = addPhoneNumberEntryPoint(phoneNumber).getOrElse { throw Exception("There was an error adding Person") }
            call.respond(addedPhoneNumber)
        }
        put {

        }
    }

}