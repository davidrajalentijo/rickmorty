package com.draja.rickmorty.domain.model


data class CharactersModel(
    val info: PaginationModel,
    val results: List<CharacterModel>
)

data class PaginationModel(
    val count: Int = 0,
    val pages: Int = 0,
    val next: String? = "",
    val prev: String? = ""
)

data class CharacterModel(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String ="",
    val type: String = "",
    val gender: String = "",
    val origin: OriginModel = OriginModel(),
    val location: LocationModel = LocationModel(),
    val image: String = "",
    val episode: List<String> = emptyList(),
)

data class OriginModel(
    val name: String ="",
    val url: String =""
)

data class LocationModel(
    val name: String = "",
    val url: String = ""
)
