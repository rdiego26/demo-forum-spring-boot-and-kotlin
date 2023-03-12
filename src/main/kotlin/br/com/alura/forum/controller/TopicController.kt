package br.com.alura.forum.controller

import br.com.alura.forum.dto.CreateTopic
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopic
import br.com.alura.forum.service.TopicService
import jakarta.validation.Valid
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
    fun create(@RequestBody @Valid dto: CreateTopic) {
        service.create(dto)
    }

    @PutMapping
    fun update(@RequestBody @Valid dto: UpdateTopic) {
        service.update(dto)
    }
}