package br.com.alura.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "topics")
data class Topic(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val title: String,
    val message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val course: Course,
    @ManyToOne
    val author: User,
    @Enumerated(value = EnumType.STRING)
    val status: TopicStatus = TopicStatus.NOT_ANSWERED,
    @OneToMany(mappedBy = "topic")
    val answers: List<Answer> = ArrayList()
)