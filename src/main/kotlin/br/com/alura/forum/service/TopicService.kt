package br.com.alura.forum.service

import br.com.alura.forum.dto.CreateTopic
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopic
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicCreateMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import br.com.alura.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicCreatMapper: TopicCreateMapper,
    private val notFoundMessage: String = "Topic not found!"
) {

    fun list(courseName: String? = null, pagination: Pageable): Page<TopicView> {
        val topics: Page<TopicView> = if (courseName.isNullOrBlank()) {
            repository.findAll(pagination).map { t -> topicViewMapper.map(t) }
        } else {
            repository.findByCourseName(courseName, pagination).map { t -> topicViewMapper.map(t) }
        }
        return topics
    }

    fun findById(id: Long): TopicView? {
        val fetchedTopic = repository.findById(id).orElseThrow { throw NotFoundException(notFoundMessage) }

        return fetchedTopic.let { t -> topicViewMapper.map(t) }
    }

    fun create(dto: CreateTopic): TopicView {
        val converted = topicCreatMapper.map(dto)
        repository.save(converted)

        return topicViewMapper.map(converted)
    }

    fun update(dto: UpdateTopic): TopicView {
        val fetchedTopic = repository.findById(dto.id).orElseThrow { throw NotFoundException(notFoundMessage) }

        val updatedTopic = Topic(
            id = fetchedTopic?.id,
            title = dto.title,
            message = dto.message,
            author = fetchedTopic!!.author,
            course = fetchedTopic.course,
            answers = fetchedTopic.answers,
            status = fetchedTopic.status,
            createdAt = fetchedTopic.createdAt
        )
        repository.save(updatedTopic)

        return topicViewMapper.map(updatedTopic)
    }

    fun delete(id: Long) {
        val fetchedTopic = repository.findById(id).orElseThrow { throw NotFoundException(notFoundMessage) }

        repository.delete(fetchedTopic)
    }
}