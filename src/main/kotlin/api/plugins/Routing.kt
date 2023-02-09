package api.plugins

import api.routes.adderessRouting
import api.routes.emailRouting
import api.routes.personRouting
import api.routes.phoneNumberRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(){
    routing {
        personRouting()
        phoneNumberRouting()
        emailRouting()
        adderessRouting()
    }
}