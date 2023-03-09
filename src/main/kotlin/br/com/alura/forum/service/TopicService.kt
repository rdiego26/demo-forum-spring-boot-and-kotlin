package br.com.alura.forum.service

import br.com.alura.forum.dto.CreateTopic
import br.com.alura.forum.model.Course
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: List<Topic> = listOf(),
    private val courseService: CourseService,
    private val userService: UserService
) {

    fun list(): List<Topic> {
        return topics
    }

    fun findById(id: Long): Topic? {
        return topics.find { id == it.id }
    }

    fun create(dto: CreateTopic) {

        val fetchedCourse: Course? = courseService.findById(dto.idCourse)
        val fetchedUser: User? = userService.findById(dto.idAuthor)

        topics = topics.plus(Topic(
            id = topics.size.toLong() + 1,
            title = dto.title,
            message = dto.message,
            course = fetchedCourse!!,
            author = fetchedUser!!
        ))
    }
}