package com.ba.pokedex.ui.adapter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ba.pokedex.coroutines.ICoroutineContextProvider
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.usecases.IGetPokemonUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class PokemonPagingSource(
    private val getPokemonsPageUseCase: IGetPokemonUseCase,
    private val dispatcher: ICoroutineContextProvider
) : PagingSource<Int, PokemonItemUIModel>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonItemUIModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItemUIModel> {
        return try {
            val page = params.key ?: 0

            if (page != 0) delay(2000)

            val response = withContext(dispatcher.getIoContext()) {
                getPokemonsPageUseCase.execute(params.loadSize, page * params.loadSize)
            }

            Log.d("PokemonPagingSource", "Page: $page, Size: ${params.loadSize}, Response: $response")

            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}