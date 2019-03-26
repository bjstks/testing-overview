package io.sample

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
@AutoConfigureWireMock(port = 9999)
@ActiveProfiles("test")
class ApplicationTests {

    @LocalServerPort
    var port: Int = 0

    val rest = RestTemplate()

    @Before
    fun setUp() {
        stubFor(get(urlEqualTo("/api/v1/players"))
            .willReturn(aResponse().withBody("neat")))
    }

    @Test
    fun `returns all players`() {
        assertEquals("neat", rest.getForEntity("http://localhost:$port/data", String::class.java).body)
    }
}
