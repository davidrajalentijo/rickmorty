package com.draja.rickmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.draja.rickmorty.data.datasource.CharacterCloudDataSource
import com.draja.rickmorty.data.network.response.CharacterResponse

class CharactersPaginationSource(
    private val characterCloudDataSource: CharacterCloudDataSource
) : PagingSource<Int, CharacterResponse>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? =
        state.anchorPosition

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        val currentPage = params.key ?: 1
        return try {
            val result = characterCloudDataSource.getAllCharacters(currentPage)
            if (result.isFailure) return LoadResult.Error(Exception("Pagination Error"))
            val data = result.getOrNull()?.results ?: emptyList()
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}