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
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.draja.rickmorty.data.network.response.CharacterResponse
import com.draja.rickmorty.navigation.Screens
import com.draja.rickmorty.domain.model.CharacterModel
import com.draja.rickmorty.domain.model.mapper.toModel
import com.draja.ui.foundations.Spacing
import com.draja.ui.extensions.setTestID
import com.draja.ui.theme.Colors
import org.koin.androidx.compose.koinViewModel

const val IMAGE_ROUNDING = 50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListView(
    viewModel: CharacterListViewModel = koinViewModel(),
    navController: NavController
) {

    val characters = viewModel.charactersPagination.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = com.draja.ui.R.string.Characters_title)) },
            )
        },
        containerColor = Colors.greenBackground
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            when {
                characters.loadState.refresh is LoadState.Loading -> {

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                characters.loadState.refresh is LoadState.Error -> {
                    ErrorView {
                        characters.retry()
                    }
                }

                characters.loadState.append is LoadState.Error -> {
                    ErrorView {
                        characters.retry()
                    }
                }

                else -> {
                    CardList(characters, navController)
                }
            }
        }
    }
}

@Composable
fun CardList(
    characters: LazyPagingItems<CharacterResponse>,
    navController: NavController,
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(characters.itemCount) { index ->
            val item = characters[index]
            if (item != null) {
                CardItem(character = item.toModel(), navController)
            }
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
                navController.navigate(Screens.CharacterDetailScreens.createRoute(character.id.toString()))
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
                    .clip(RoundedCornerShape(IMAGE_ROUNDING))
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