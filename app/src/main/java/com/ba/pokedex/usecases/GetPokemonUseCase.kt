package com.ba.pokedex.usecases

import com.ba.pokedex.database.entity.toUIModel
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource


interface IGetPokemonUseCase {
    suspend fun execute(limit: Int, offset: Int): List<PokemonItemUIModel>
}

class GetPokemonUseCase(private val pokemonRemoteDataSource: IPokemonDataSource.Repository) :
    IGetPokemonUseCase {
    override suspend fun execute(limit: Int, offset: Int): List<PokemonItemUIModel> {
        return pokemonRemoteDataSource.getPokemonsPagedLocal(limit, offset).map { pokemonEntity ->
            pokemonEntity.toUIModel()
        }
    }
}