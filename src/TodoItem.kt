package com.ktor.sample

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import java.time.LocalDate

data class TodoItem(
    val id: Int,
    val title: String,
    val details: String,
    val assignedTo: String, /*
    @JsonSerialize(using = ToStringSerializer::class)
    @JsonDeserialize(using = LocalDateDeserializer::class)
    val dueDate: LocalDate, */
    val importance: Importance
)

enum class Importance {
    LOW, MEDIUM, HIGH
}

val todo1 = TodoItem(
    0,
    "app REST API data access",
    "details",
    "me",
   // LocalDate.of(2020, 4, 12),
    Importance.HIGH
)


val todo2 = TodoItem(
    1,
    "Add REST API service",
    "more details",
    "me also",
  //  LocalDate.of(2020, 4, 13),
    Importance.LOW
)

var todoList = listOf(todo1, todo2)