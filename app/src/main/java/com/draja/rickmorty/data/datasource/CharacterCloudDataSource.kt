package com.draja.rickmorty.data.datasource

import com.draja.network.NetworkManager
import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse
import com.draja.rickmorty.data.network.CharactersRestService
import retrofit2.HttpException

class CharacterCloudDataSource(
    private val baseUrl: String? = null
) {

    @Suppress("TooGenericExceptionCaught")
    suspend fun getAllCharacters(page: Int): Result<CharactersResponse> =
        try {
            val api =
                NetworkManager.getInstance()?.createApi(CharactersRestService::class.java, baseUrl)

            val response = api?.getAllCharacters(page)

            response?.let {
                return Result.success(it)
            } ?: Result.failure(Exception("Characters not found"))
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }

    @Suppress("TooGenericExceptionCaught")
    suspend fun getCharacterById(id: String): Result<CharacterResponse> =
        try {
            val api = NetworkManager.getInstance()?.createApi(CharactersRestService::class.java, baseUrl)

            val response = api?.getCharacterById(id)

            response?.let {
                return Result.success(it)
            } ?: Result.failure(Exception("Character not found"))
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
}