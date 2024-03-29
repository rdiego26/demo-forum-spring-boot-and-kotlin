package br.com.alura.forum.controller

import br.com.alura.forum.dto.CreateTopic
import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopic
import br.com.alura.forum.service.TopicService
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/topics")
class TopicController(private val service: TopicService) {

    @GetMapping
    @Cacheable("topic_list")
    fun list(@RequestParam(required = false) courseName: String?,
             pagination: Pageable): Page<TopicView> {
        return service.list(courseName, pagination)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicView? {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topic_list"], allEntries = true)
    fun create(
        @RequestBody @Valid dto: CreateTopic,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topicView = service.create(dto)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topic_list"], allEntries = true)
    fun update(
        @RequestBody @Valid dto: UpdateTopic,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topicView = service.update(dto)

        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = ["topic_list"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @GetMapping("/report")
    fun report(): List<TopicByCategoryDto> {
        return service.report()
    }
}