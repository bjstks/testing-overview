package io.sample

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class DataServiceTests {

    private val host = "http://www.testing-overview.io"

    @MockK
    private var rest = mockk<RestTemplate>()

    @Test(expected = KotlinNullPointerException::class)
    fun `it should throw error when response has no content`() {
        every {
            rest.getForEntity<String>("$host/api/v1/players", String::class.java)
        } returns ResponseEntity.noContent().build()

        DataService(host, rest).allPlayers()
    }

    @Test
    fun `it should return a string when request succeeds`() {
        every {
            rest.getForEntity<String>("$host/api/v1/players", String::class.java)
        } returns ResponseEntity.ok("sweet.")

        assertEquals("sweet.", DataService(host, rest).allPlayers())

        verify { rest.getForEntity<String>("$host/api/v1/players", String::class.java) }

        confirmVerified(rest)
    }
}
