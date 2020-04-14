package com.ktor.sample

import io.ktor.application.call
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.method
import io.ktor.routing.route

fun Routing.todoApi() {
    route("api") {
        route("todo", HttpMethod.Get) {
            handle {
                call.respond(todoList)
            }
        }

        route("todo", HttpMethod.Post) {
            handle {
                call.respond(HttpStatusCode.Unauthorized)
            }
        }

    }
}