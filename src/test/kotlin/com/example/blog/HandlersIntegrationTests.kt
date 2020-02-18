package com.example.blog

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class HandlersIntegrationTests(@Autowired val client: WebTestClient) {

    @Test
    fun `List articles`() {
        client.get().uri("/api/v2/article/").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("\$.[0].author.login").isEqualTo("smaldini")
                .jsonPath("\$.[0].slug").isEqualTo("reactor-aluminium-has-landed")
                .jsonPath("\$.[1].author.login").isEqualTo("smaldini")
                .jsonPath("\$.[1].slug").isEqualTo("reactor-bismuth-is-out")
    }

    @Test
    fun `List users`() {
        client.get().uri("/api/v2/user/").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("\$.[0].login").isEqualTo("smaldini")
    }
}