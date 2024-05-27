package com.draja.rickmorty.ui.characterdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.draja.rickmorty.domain.model.CharacterModel
import com.draja.ui.R
import com.draja.ui.components.GroupRow
import com.draja.ui.foundations.Spacing
import com.draja.ui.ViewState
import com.draja.ui.extensions.setTestID
import com.draja.ui.theme.Colors
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailView(
    viewModel: CharacterDetailsViewModel = koinViewModel(),
    characterId: String,
    backNavigation: () -> Unit
) {

    LaunchedEffect(true) {
        viewModel.getCharacterById(characterId)
    }

    val character by viewModel.character.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { backNavigation() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = Colors.greenBackground
    ) { paddingValues ->

        when (character) {
            is ViewState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ViewState.Success -> {
                val characterData = (character as ViewState.Success).data
                SuccessView(paddingValues, characterData)
            }

            is ViewState.Error -> {
                ErrorView {
                    viewModel.getCharacterById(characterId)
                }
            }

            ViewState.Idle -> {}
            else -> {}
        }
    }
}

@Composable
fun SuccessView(
    paddingValues: PaddingValues,
    characterData: CharacterModel
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(Spacing.m)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(Spacing.m)
    ) {

        Text(
            modifier = Modifier.setTestID(CharacterDetailsIds.CHARACTER_NAME),
            text = characterData.name,
            style = MaterialTheme.typography.headlineMedium
        )

        AsyncImage(
            model = characterData.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .setTestID(CharacterDetailsIds.CHARACTER_IMAGE)
                .height(Spacing.xxxl)
        )

        CharacterInfoCard(characterData)
    }
}

@Composable
fun CharacterInfoCard(characterData: CharacterModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(Spacing.m),
            verticalArrangement = Arrangement.spacedBy(Spacing.s)
        ) {
            GroupRow(
                modifier = Modifier.setTestID(CharacterDetailsIds.CHARACTER_STATUS),
                label = stringResource(R.string.Character_status_type),
                value = characterData.status
            )
            GroupRow(
                modifier = Modifier.setTestID(CharacterDetailsIds.CHARACTER_SPECIES),
                label = stringResource(R.string.Character_status_species),
                value = characterData.species
            )
            if (characterData.type.isNotEmpty()) {
                GroupRow(
                    modifier = Modifier.setTestID(CharacterDetailsIds.CHARACTER_TYPE),
                    label = stringResource(R.string.Character_status_type),
                    value = characterData.type
                )
            }
            GroupRow(
                modifier = Modifier.setTestID(CharacterDetailsIds.CHARACTER_GENDER),
                label = stringResource(R.string.Character_status_gender),
                value = characterData.gender
            )
            GroupRow(
                modifier = Modifier.setTestID(CharacterDetailsIds.CHARACTER_ORIGIN),
                label = stringResource(R.string.Character_status_origin),
                value = characterData.origin.name
            )
            GroupRow(
                modifier = Modifier.setTestID(CharacterDetailsIds.CHARACTER_LOCATION),
                label = stringResource(R.string.Character_status_location),
                value = characterData.location.name
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
            .setTestID(CharacterDetailsIds.CHARACTER_DETAILS_ERROR_VIEW),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(com.draja.ui.R.string.GLOBAL_ERROR),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(Spacing.m))
        Button(onClick = onRetry) {
            Text(text = stringResource(com.draja.ui.R.string.Try_again))
        }
    }
}