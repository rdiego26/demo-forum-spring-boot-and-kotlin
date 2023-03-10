package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class CreateTopic (
    @field:NotEmpty @field:Size(min = 5, max = 80) val title: String,
    @field:NotEmpty val message: String,
    @field:Positive val idCourse: Long,
    @field:Positive val idAuthor: Long
)