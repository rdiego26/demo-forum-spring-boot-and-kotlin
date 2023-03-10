package br.com.alura.forum.mapper

import br.com.alura.forum.dto.CreateTopic
import br.com.alura.forum.model.Topic
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicCreateMapper(
    private val courseService: CourseService,
    private val userService: UserService,
): Mapper<CreateTopic, Topic> {
    override fun map(t: CreateTopic): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.findById(t.idCourse)!!,
            author = userService.findById(t.idAuthor)!!
        )
    }
}