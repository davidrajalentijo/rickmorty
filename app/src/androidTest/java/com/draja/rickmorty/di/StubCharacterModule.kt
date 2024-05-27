package com.draja.rickmorty.di

import com.draja.rickmorty.ui.characterlist.CharacterListViewModel
import com.draja.rickmorty.data.StubCharacterRepositoryImpl
import com.draja.rickmorty.data.datasource.CharacterCloudDataSource
import com.draja.rickmorty.data.repository.CharacterRepository
import com.draja.rickmorty.domain.GetAllCharactersUseCase
import com.draja.rickmorty.domain.GetCharacterByIdUseCase
import com.draja.rickmorty.ui.characterdetail.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val stubCharacterDetailsModule = module {
    single { CharacterCloudDataSource() }
    single<CharacterRepository> { StubCharacterRepositoryImpl() }

    single { GetCharacterByIdUseCase(get()) }
    single { GetAllCharactersUseCase(get()) }

    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailsViewModel(get()) }
}