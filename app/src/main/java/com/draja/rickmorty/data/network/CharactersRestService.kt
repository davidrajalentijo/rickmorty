package com.draja.rickmorty.data.network

import com.draja.rickmorty.data.network.CharactersConstants.GET_CHARACTER
import com.draja.rickmorty.data.network.CharactersConstants.GET_CHARACTER_BY_ID
import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersRestService {

    @GET(GET_CHARACTER)
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): CharactersResponse

    @GET(GET_CHARACTER_BY_ID)
    suspend fun getCharacterById(
        @Path("id")
        id: String
    ): CharacterResponse
}