package br.com.alura.forum.service

import br.com.alura.forum.dto.CreateTopic
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.model.Course
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.User
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
    private var topics: List<Topic> = listOf(),
    private val courseService: CourseService,
    private val userService: UserService
) {

    fun list(): List<TopicView> {
        return topics.stream().map { t -> TopicView(
            id = t.id,
            title = t.title,
            message = t.message,
            status = t.status,
            createdAt = t.createdAt
        ) }.collect(Collectors.toList())
    }

    fun findById(id: Long): TopicView? {
        val fetchedTopic = topics.find { id == it.id }
        return fetchedTopic.let { t -> TopicView(
            id = t!!.id,
            title = t.title,
            message = t.message,
            status = t.status,
            createdAt = t.createdAt
        ) }
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