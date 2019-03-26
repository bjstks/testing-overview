package io.sample

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DataController(private var data: DataService) {

    @GetMapping("/data")
    fun data(): ResponseEntity<String?> {
        return ResponseEntity.ok(data.allPlayers())
    }
}