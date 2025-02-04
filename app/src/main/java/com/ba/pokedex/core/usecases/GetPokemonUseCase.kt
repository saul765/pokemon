package com.ba.pokedex.core.usecases

import com.ba.pokedex.core.database.entity.PokemonEntity
import com.ba.pokedex.core.pokemon.IPokemonDataSource
import kotlinx.coroutines.flow.Flow


interface IGetPokemonUseCase {
    fun execute(): Flow<List<PokemonEntity>>
}

class GetPokemonUseCase(private val pokemonRepository: IPokemonDataSource.Repository) :
    IGetPokemonUseCase {
    override fun execute(): Flow<List<PokemonEntity>> = pokemonRepository.getPokemonsLocal()

}