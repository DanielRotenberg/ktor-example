package com.ktor.sample

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Exception

fun Routing.todoApi() {
    val logger: Logger = LoggerFactory.getLogger(Routing::class.java)
    route("api") {
        get("todo") {
            logger.debug("get todo list")
            call.respond(todoList)
        }

        get("todo/{id}") {
            val id: String = call.parameters["id"]!!

            try {
                val todoItem = todoList[id.toInt()]
                call.respond(todoItem)
            } catch (e: Throwable) {
                call.respond(HttpStatusCode.NotFound)
            }

        }

        post("todo") {
            logger.debug("hi")
            try {
                val todoItem: TodoItem = call.receive()
                logger.debug(todoItem.toString())
                val result = todoItem as? TodoItem ?: logger.debug("error converting to list item")
                logger.debug(result.toString())
                val newItem = todoItem.copy(id = 45)
                todoList = todoList + todoItem + newItem
                call.respond(HttpStatusCode.Created, todoItem)
            } catch (e: Exception) {
                logger.debug("failed to parse the item data")
            }
            logger.debug("after respond")


        }

        /*    route("todo", HttpMethod.Get) {
                handle {
                    call.respond(todoList)
                }
            }
            curl -i -X GET -H "Accept: application/json" http://localhost:8081/api/todo/0
            curl -i -X GET -H "Accept: application/json" -d @postTodo.json http://localhost:8081/api/todo
            curl -i -X GET -H "Accept: application/json" -d '{
"title":"a",
"details":"b",
"assignedTo":"c",
"dueDate":"2020-3-20",
"importance":"HIGH"
}' http://localhost:8081/api/todo


 curl -i -X GET -H "Accept: application/json" -d '{\"id\":\"5\",\"title\":\"a\",\"details\":\"b\",\"assignedTo\":\"c\",\"dueDate\":\"2020-3-15\",\"importance\":\"HIGH\"}' http://localhost:8081/api/todo


            */

    }
}