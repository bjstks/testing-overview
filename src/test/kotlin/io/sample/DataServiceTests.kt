package io.sample

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class DataServiceTests {

    val host = "http://www.testtest.io"

    @MockK
    var rest = mockk<RestTemplate>()

    @Test(expected = KotlinNullPointerException::class)
    fun `it should throw error when request fails`() {
        every {
            rest.getForEntity<String>("$host/api/v1/players", String::class.java)
        } returns ResponseEntity.badRequest().build()

        DataService(host, rest).allPlayers()
    }

    @Test
    fun `it should return a string when request succeeds`() {
        every {
            rest.getForEntity<String>("$host/api/v1/players", String::class.java)
        } returns ResponseEntity.ok("sweet.")

        assertEquals("sweet.", DataService(host, rest).allPlayers())
    }
}
