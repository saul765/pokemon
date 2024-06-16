package com.ba.pokedex.usecases

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ba.pokedex.database.entity.toUIModel
import com.ba.pokedex.domain.toEntity
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource
import com.ba.pokedex.workers.PokemonWorker

interface IGetHomePokemonUseCase {
    suspend fun execute(context: Context)
}

class GetHomePokemonUseCase(private val pokemonRepository: IPokemonDataSource.Repository) :
    IGetHomePokemonUseCase {
    companion object {
        const val POKEMON_FIRST_CHUNK = 15
        const val POKEMON_OFFSET = 0
    }

    override suspend fun execute(context: Context) {

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

            // Load the rest of the pokemons in the background
            pokedexLoadBatch(context)
        }
    }

    private fun pokedexLoadBatch(context: Context) {
        val workRequest = OneTimeWorkRequestBuilder<PokemonWorker>().build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

}