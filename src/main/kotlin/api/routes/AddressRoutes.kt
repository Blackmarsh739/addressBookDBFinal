package api.routes

import arrow.core.getOrElse
import com.example.addressbook.requests.AddAddressRequest
import entryPoints.addAddressEntryPoint
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import setDatabase

fun Route.adderessRouting(){
    setDatabase()
    route("/address"){
        post {
            val address = call.receive<AddAddressRequest>()
            val addedAddress = addAddressEntryPoint(address).getOrElse { throw Exception("There was an error adding Person") }
            call.respond(addedAddress)
        }
    }
}