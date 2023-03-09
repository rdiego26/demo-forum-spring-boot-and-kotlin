package br.com.alura.forum.controller

import br.com.alura.forum.dto.CreateTopic
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.model.Topic
import br.com.alura.forum.service.TopicService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(): List<TopicView> {
        return service.list()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicView? {
        return service.findById(id)
    }

    @PostMapping
    fun create(@RequestBody dto: CreateTopic) {
        service.create(dto)
    }
}