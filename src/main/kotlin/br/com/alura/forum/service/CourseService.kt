package br.com.alura.forum.service

import br.com.alura.forum.model.Course
import org.springframework.stereotype.Service

@Service
class CourseService(var courses: List<Course>) {

    init {
        val course = Course(
            id = 1,
            name = "Kotlin Basics",
            category = "programming-languages"
        )

        courses = listOf(course)
    }

    fun findById(id: Long): Course? {
        return courses.find { id == it.id }
    }
}
