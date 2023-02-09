package api.routes

import arrow.core.getOrElse
import com.example.addressbook.requests.EmailRequest
import entryPoints.addEmailEntryPoint
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import setDatabase

fun Route.emailRouting(){

    setDatabase()
    route("/emails"){
        post {
            val email = call.receive<EmailRequest>()
            val addedEmail = addEmailEntryPoint(email).getOrElse { throw Exception("There was an error adding Person") }
            call.respond(addedEmail)
        }

    }
}