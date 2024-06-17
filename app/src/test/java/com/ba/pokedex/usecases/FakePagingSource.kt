package com.ba.pokedex.usecases

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ba.pokedex.database.entity.PokemonEntity

class FakePagingSource : PagingSource<Int, PokemonEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        return LoadResult.Page(
            data = listOf(
                PokemonEntity(
                    id = 1,
                    2,
                    name = "Bulbasaur",
                    imageUrl = "https://pokeapi.co/api/v2/pokemon/1/"
                ),
                PokemonEntity(
                    id = 2,
                    2,
                    name = "Ivysaur",
                    imageUrl = "https://pokeapi.co/api/v2/pokemon/2/"
                )
            ),
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return null
    }
}