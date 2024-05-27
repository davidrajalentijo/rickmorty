package com.draja.rickmorty.ui.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.draja.rickmorty.domain.GetCharacterByIdUseCase
import com.draja.rickmorty.domain.model.CharacterModel
import com.draja.ui.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : ViewModel() {

    private val _character = MutableStateFlow<ViewState<CharacterModel>>(ViewState.Idle)
    val character = _character.asStateFlow()

    fun getCharacterById(id: String) {
        _character.value = ViewState.Loading
        viewModelScope.launch {
            getCharacterByIdUseCase.getCharacterById(id).onSuccess {
                _character.value = ViewState.Success(it)
            }.onFailure {
                _character.value = ViewState.Error(it)
            }
        }
    }
}