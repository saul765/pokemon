package com.ba.pokedex.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ba.pokedex.database.entity.PokemonEntity
import com.ba.pokedex.database.entity.toUIModel
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface IGetPokemonUseCase2 {
    fun execute(): Flow<PagingData<PokemonItemUIModel>>
}

class GetPokemon2UseCase(private val pokemonRepository: IPokemonDataSource.Repository) :
    IGetPokemonUseCase2 {
    override fun execute(): Flow<PagingData<PokemonItemUIModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { pokemonRepository.getPokemonsPaged2() }
        ).flow.map { pagingData ->
            pagingData.map { pokemonEntity ->
                pokemonEntity.toUIModel()
            }
        }
    }
}