package com.example.blog

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.notFound
import org.springframework.web.servlet.function.ServerResponse.ok

@Component
class ArticleHandler(private val repository: ArticleRepository) {

    fun findAll(req: ServerRequest) = ok().contentType(APPLICATION_JSON).body(repository.findAllByOrderByAddedAtDesc())

    fun findOne(req: ServerRequest) = repository.findBySlug(req.pathVariable("slug"))
            ?.let { ok().contentType(APPLICATION_JSON).body(it) }
            ?: notFound().build()
}

@Component
class UserHandler(private val repository: UserRepository) {

    fun findAll(req: ServerRequest) = ok().contentType(APPLICATION_JSON).body(repository.findAll())

    fun findOne(req: ServerRequest): ServerResponse {
        val user = repository.findByLogin(req.pathVariable("login"))
        return if (user != null) {
            ok().contentType(APPLICATION_JSON).body(user)
        } else {
            notFound().build()
        }
    }
}