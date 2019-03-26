package io.sample

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class DataService(
    @Value("\${data.host}")
    private var host: String,
    private var rest: RestTemplate
) {

    fun allPlayers(): String {
        return rest
            .getForEntity("$host/api/v1/players", String::class.java)
            .body!!
    }
}