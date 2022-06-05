package ru.nsu.belozerov

import java.text.SimpleDateFormat
import java.util.*

class TaskBuilder {

    private val name: String = ""
    private val description: String = ""
    private val mark: Int = 0

    private var softDeadline: Date = Date()
    var soft: String = ""
        set(value) {
            softDeadline= SimpleDateFormat("dd-MM-yyyy").parse(value)
        }

    private var hardDeadline: Date = Date()
    var hard: String = ""
        set(value) {
            hardDeadline= SimpleDateFormat("dd-MM-yyyy").parse(value)
        }

    fun build(): Task = Task(name, description, softDeadline, hardDeadline, mark)

}