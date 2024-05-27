package com.draja.rickmorty.ui.characterlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.draja.rickmorty.Screen
import com.draja.rickmorty.domain.model.CharacterModel
import com.draja.ui.theme.greenBackground
import com.draja.ui.foundations.Spacing
import com.draja.ui.ViewState
import com.draja.ui.extensions.setTestID
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListView(
    viewModel: CharacterListViewModel = koinViewModel(),
    navController: NavController
) {

    val charactersList by viewModel.characters.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = com.draja.ui.R.string.Characters_title)) },
            )
        },
        containerColor = greenBackground
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            when (charactersList) {
                is ViewState.Loading -> {

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is ViewState.Success -> {
                    CardList(characters = (charactersList as ViewState.Success).data, navController)
                }

                is ViewState.Error -> {
                    ErrorView {
                        viewModel.getAllCharacters()
                    }
                }

                else -> {}
            }

        }
    }
}

@Composable
fun CardList(
    characters: List<CharacterModel>,
    navController: NavController
) {
    LazyColumn {
        items(characters) { character ->
            CardItem(character = character, navController)
        }
    }
}

@Composable
fun CardItem(
    character: CharacterModel,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .setTestID(CharacterListIds.CHARACTER_LIST_ELEMENT)
            .padding(Spacing.m)
            .clickable {
                navController.navigate(Screen.CharacterDetailScreen.createRoute(character.id.toString()))
            },
        shape = RoundedCornerShape(Spacing.s)
    ) {
        Row(
            modifier = Modifier
                .padding(Spacing.m)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .size(Spacing.xxl),
                contentScale = ContentScale.Crop
            )
            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(Spacing.s)
            )
        }
    }
}

@Composable
fun ErrorView(
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .setTestID(CharacterListIds.CHARACTER_LIST_ERROR_VIEW),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = com.draja.ui.R.string.GLOBAL_ERROR),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(Spacing.m))
        Button(onClick = onRetry) {
            Text(text = stringResource(id = com.draja.ui.R.string.Try_again))
        }
    }
}