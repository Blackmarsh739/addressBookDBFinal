package api

import api.plugins.configureRouting
import api.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(){
    configureRouting()
    configureSerialization()
}