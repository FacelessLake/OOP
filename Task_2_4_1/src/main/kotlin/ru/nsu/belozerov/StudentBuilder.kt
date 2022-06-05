package ru.nsu.belozerov

class StudentBuilder {

    private val name: String = ""
    private val surname: String = ""
    private val gitHub: String = ""
    private val group: Int = 0

    private val tasks = mutableListOf<Task>()

    fun task(block: TaskBuilder.() -> Unit) {
        tasks.add(TaskBuilder().apply(block).build())
    }

    fun build(): Student = Student(name, surname, gitHub, group, tasks)

}