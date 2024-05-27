package com.draja.rickmorty.domain.model.mapper

import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.data.network.response.CharactersResponse
import com.draja.rickmorty.data.network.response.LocationResponse
import com.draja.rickmorty.data.network.response.OriginResponse
import com.draja.rickmorty.domain.model.CharacterModel
import com.draja.rickmorty.domain.model.CharactersModel
import com.draja.rickmorty.domain.model.LocationModel
import com.draja.rickmorty.domain.model.OriginModel

fun CharactersResponse.toModel(): CharactersModel =
    CharactersModel(
        results = results.map { it.toModel() }
    )

fun CharacterResponse.toModel(): CharacterModel =
    CharacterModel(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.toModel(),
        location = location.toModel(),
        image = image,
        episode = episode
    )

fun OriginResponse.toModel(): OriginModel =
    OriginModel(
        name = name,
        url = url
    )

fun LocationResponse.toModel(): LocationModel =
    LocationModel(
        name = name,
        url = url
    )