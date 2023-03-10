package br.com.alura.forum.service

import br.com.alura.forum.dto.CreateTopic
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.mapper.TopicCreateMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Course
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.User
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
}