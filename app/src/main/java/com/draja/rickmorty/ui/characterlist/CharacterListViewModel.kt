package com.draja.rickmorty.ui.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.draja.rickmorty.domain.GetAllCharactersUseCase
import com.draja.rickmorty.domain.model.CharacterModel
import com.draja.ui.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
) : ViewModel() {

    private val _characters = MutableStateFlow<ViewState<List<CharacterModel>>>(ViewState.Idle)
    val characters = _characters.asStateFlow()

    init {
        getAllCharacters()
    }

    fun getAllCharacters() {
        _characters.value = ViewState.Loading
        viewModelScope.launch {
            getAllCharactersUseCase.getAllCharacters().onSuccess {
                _characters.value = ViewState.Success(it.results)
            }.onFailure {
                _characters.value = ViewState.Error(it)
            }
        }
    }
}