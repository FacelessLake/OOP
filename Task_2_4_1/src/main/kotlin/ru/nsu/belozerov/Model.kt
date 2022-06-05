package ru.nsu.belozerov

import java.util.*

data class Student(
    val name: String,
    val surname: String,
    val gitHub: String,
    val group: Int,
    val tasks: List<Task>,
)

data class Group(
    val number: Int,
    val marks: List<Student>
)

data class Task(
    val name: String,
    val description: String,
    val softDeadline: Date,
    val hardDeadline: Date,
    val mark: Int
)
