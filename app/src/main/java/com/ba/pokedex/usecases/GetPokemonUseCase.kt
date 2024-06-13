package com.ba.pokedex.usecases

import com.ba.pokedex.domain.PokemonResult
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource

interface IGetPokemonUseCase {
    suspend fun execute(): PokemonResult
}

class GetPokemonUseCase(private val pokemonRepository: IPokemonDataSource.Repository) :
    IGetPokemonUseCase {
    override suspend fun execute(): PokemonResult {
        return pokemonRepository.getPokemonsAsync(20, 0)
    }

}