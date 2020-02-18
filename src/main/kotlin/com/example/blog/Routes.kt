package com.example.blog

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.router

@Configuration
class Routes(val articleHandler: ArticleHandler,
             val userHandler: UserHandler) {

    @Bean
    fun apiRoutes() = router {
        (accept(MediaType.APPLICATION_JSON) and "/api/v2").nest {
            "/article".nest {
                GET("/", articleHandler::findAll)
                GET("/{slug}", articleHandler::findOne)
            }
            "/user".nest {
                GET("/", userHandler::findAll)
                GET("/{login}", userHandler::findOne)
            }
        }
    }
}