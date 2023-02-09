package api.plugins

import api.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(){
    routing {
        personRouting()
        phoneNumberRouting()
        emailRouting()
        adderessRouting()
        groupRouting()
    }
}