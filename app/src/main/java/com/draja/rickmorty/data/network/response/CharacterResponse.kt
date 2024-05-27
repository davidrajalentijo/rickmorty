package com.draja.rickmorty.data.network.response

data class CharactersResponse(
    val info: InfoResponse = InfoResponse(),
    val results: List<CharacterResponse> = emptyList()
)

data class InfoResponse(
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = "",
    val prev: String? = ""
)

data class CharacterResponse(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: OriginResponse = OriginResponse(),
    val location: LocationResponse = LocationResponse(),
    val image: String = "",
    val episode: List<String> = emptyList(),
)

data class OriginResponse(
    val name: String = "",
    val url: String = ""
)

data class LocationResponse(
    val name: String = "",
    val url: String = ""
)