package com.ba.pokedex.usecases

import com.ba.pokedex.domain.toUIModel
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource

interface IGetHomePokemonUseCase {
    suspend fun execute(): List<PokemonItemUIModel>
}

class GetHomePokemonUseCase(private val pokemonRepository: IPokemonDataSource.Repository) :
    IGetHomePokemonUseCase {
    override suspend fun execute(): List<PokemonItemUIModel> {
        val pokemonResult = pokemonRepository.getPokemonsAsync(15, 0)

        val pokemonItems = pokemonResult.results.map { pokemon ->
            pokemonRepository.getPokemonDetailAsync(pokemon.url)
        }

        return pokemonItems.map { pokemonItem ->
            pokemonItem.toUIModel()
        }
    }

}