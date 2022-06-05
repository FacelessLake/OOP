package ru.nsu.belozerov


class groupBuilder {

    var number: Int = 0
    private val students = mutableListOf<Student>()

    fun student(block: StudentBuilder.() -> Unit) {
        students.add(StudentBuilder().apply(block).build())
    }

    fun build() : Group = Group(number, students)

}