package br.com.alura.forum.service

import br.com.alura.forum.dto.CreateTopic
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopic
import br.com.alura.forum.mapper.TopicCreateMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
    private var topics: List<Topic> = listOf(),
    private val topicViewMapper: TopicViewMapper,
    private val topicCreatMapper: TopicCreateMapper
) {

    fun list(): List<TopicView> {
        return topics.stream().map { t -> topicViewMapper.map(t) }.collect(Collectors.toList())
    }

    fun findById(id: Long): TopicView? {
        val fetchedTopic = topics.find { id == it.id }
        return fetchedTopic.let { t -> topicViewMapper.map(t!!) }
    }

    fun create(dto: CreateTopic) {
        val converted = topicCreatMapper.map(dto)
        converted.id = (topics.size + 1).toLong()
        topics = topics.plus(converted)
    }

    fun update(dto: UpdateTopic) {
        val fetchedTopic = topics.find { dto.id == it.id }
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
        topics = topics.minus(fetchedTopic).plus(updatedTopic)
    }

    fun delete(id: Long) {
        val fetchedTopic = topics.find { id == it.id }
        topics = topics.minus(fetchedTopic!!)

    }
}