package br.com.alura.forum.service

import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class UserService(var authors: List<User>) {

    init {
        val user = User(
            id = 1,
            name = "User #1",
            email = "user_1@gmail.com"
        )

        authors = listOf(user)
    }

    fun findById(id: Long): User? {
        return authors.find { id == it.id }
    }
}
