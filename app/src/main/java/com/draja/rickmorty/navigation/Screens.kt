package com.draja.rickmorty.navigation

sealed class Screens(val route: String) {
    data object CharactersListScreens : Screens("charactersList")
    data object CharacterDetailScreens : Screens("characterDetail/{characterId}") {
        fun createRoute(characterId: String) = "characterDetail/$characterId"
    }
}