package com.draja.rickmorty.data.datasource.cloud

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.draja.rickmorty.data.datasource.CharacterCloudDataSource
import com.draja.rickmorty.fakedata.charactersResponse
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CharacterCloudDataSourceTest {

    private val mockWebServer = MockWebServer()
    private lateinit var characterCloudDataSource: CharacterCloudDataSource

    @Before
    fun setUp() {
        mockWebServer.start(8080)
        characterCloudDataSource = CharacterCloudDataSource()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get all characters OK`() = runBlocking {
        val jsonResponse = this.javaClass.classLoader
            .getResourceAsStream("test/assets/characters_response.json")
            .bufferedReader().use { it.readText() }

        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .setBody(jsonResponse)

        mockWebServer.enqueue(response)

        val actual = characterCloudDataSource.getAllCharacters()

        assertThat(actual.getOrNull()).isEqualTo(charactersResponse)
    }

    @Test
    fun `get all characters 500 Error`() {
        runBlocking {
            val response = MockResponse()
                .setResponseCode(500)
            mockWebServer.enqueue(response)

            val actual = characterCloudDataSource.getAllCharacters()

            assertTrue(actual.isFailure)
            assertThat(actual.exceptionOrNull()?.message).isNotNull()
        }
    }
}