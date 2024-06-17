package com.ba.pokedex.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ba.pokedex.database.entity.toUIModel
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface IGetPokemonUseCase {
    fun execute(): Flow<PagingData<PokemonItemUIModel>>
}

class GetPokemonUseCase(private val pokemonRepository: IPokemonDataSource.Repository) :
    IGetPokemonUseCase {
    override fun execute(): Flow<PagingData<PokemonItemUIModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 3,
                initialLoadSize = 15
            ),
            pagingSourceFactory = { pokemonRepository.getPokemonsPaged() }
        ).flow.map { pagingData ->
            pagingData.map { pokemonEntity ->
                pokemonEntity.toUIModel()
            }
        }
    }
}