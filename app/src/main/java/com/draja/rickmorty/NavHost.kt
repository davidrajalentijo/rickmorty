package com.draja.rickmorty

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.draja.rickmorty.ui.characterdetail.CharacterDetailView
import com.draja.rickmorty.ui.characterlist.CharactersListView

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(navController, Screen.CharactersListScreen.route) {
        composable(Screen.CharactersListScreen.route) {
            CharactersListView(navController = navController)
        }
        composable(Screen.CharacterDetailScreen.route) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")
            if (characterId != null) {
                CharacterDetailView(characterId = characterId) {
                    navController.popBackStack()
                }
            }
        }
    }
}