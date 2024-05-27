package com.draja.rickmorty.ui.characterlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.draja.rickmorty.domain.GetAllCharactersUseCase
import com.draja.rickmorty.domain.model.mapper.toModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach

class CharacterListViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    val charactersPagination = Pager(PagingConfig(pageSize = 20)) {
        getAllCharactersUseCase.getCharactersPaginated()
    }.flow.cachedIn(viewModelScope).onEach { pagingData ->
        pagingData.map { it.toModel() }
    }.catch { it }
}