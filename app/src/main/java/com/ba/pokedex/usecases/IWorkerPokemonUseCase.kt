package com.ba.pokedex.usecases

import com.ba.pokedex.domain.toEntity
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource

interface IWorkerPokemonUseCase {
    suspend fun execute(offset: Int, limit: Int): Int

}

class WorkerPokemonUseCase(private val pokemonRepository: IPokemonDataSource.Repository) :
    IWorkerPokemonUseCase {

    override suspend fun execute(offset: Int, limit: Int): Int {

        val pokemons = pokemonRepository.getPokemonsAsync(limit, offset)

        val pokemonEntities = pokemons.results.map { pokemon ->
            pokemonRepository.getPokemonDetailAsync(pokemon.url).toEntity()
        }

        pokemonRepository.savePokemonsLocal(pokemonEntities)

        return pokemonRepository.getTotalNumberOfPokemonsLocal()
    }
}