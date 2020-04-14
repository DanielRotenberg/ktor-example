package com.ktor.sample

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.application.Application
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Text
import io.ktor.jackson.jackson
import io.ktor.response.*
import io.ktor.routing.Routing

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    this.install(Routing) {
        todoApi()
    }

    install(StatusPages) {
        exception<Throwable> {
            this.call.respondText(it.localizedMessage, ContentType.Text.Plain)
            throw it
        }
    }
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }



}

