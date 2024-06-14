package com.ba.pokedex.usecases

import com.ba.pokedex.database.entity.toUIModel
import com.ba.pokedex.domain.toEntity
import com.ba.pokedex.domain.toUIModel
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource

interface IGetHomePokemonUseCase {
    suspend fun execute(): List<PokemonItemUIModel>
}

class GetHomePokemonUseCase(private val pokemonRepository: IPokemonDataSource.Repository) :
    IGetHomePokemonUseCase {
    companion object {
        const val POKEMON_FIRST_CHUNK = 15
        const val POKEMON_OFFSET = 0
    }

    override suspend fun execute(): List<PokemonItemUIModel> {

        if (pokemonRepository.getPokemonsLocal().isEmpty()) {

            // Get the first chunk of pokemons
            val pokemonResult =
                pokemonRepository.getPokemonsAsync(POKEMON_FIRST_CHUNK, POKEMON_OFFSET)

            // Get the details of the first chunk of pokemons
            val pokemonItems = pokemonResult.results.map { pokemon ->
                pokemonRepository.getPokemonDetailAsync(pokemon.url)
            }

            // Save the first chunk of pokemons to the local database
            val pokemonEntities = pokemonItems.map { pokemonItem ->
                pokemonItem.toEntity()
            }
            pokemonRepository.savePokemonsLocal(pokemonEntities)
        }

        return pokemonRepository.getPokemonsLocal().map { pokemonEntity ->
            pokemonEntity.toUIModel()
        }
    }

}