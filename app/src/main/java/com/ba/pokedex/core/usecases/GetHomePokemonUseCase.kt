package com.ba.pokedex.core.usecases

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ba.pokedex.core.domain.toEntity
import com.ba.pokedex.core.pokemon.IPokemonDataSource
import com.ba.pokedex.core.workers.PokemonWorker
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

interface IGetHomePokemonUseCase {
    suspend fun execute(): Flow<Unit>
}

class GetHomePokemonUseCase(
    private val pokemonRepository: IPokemonDataSource.Repository,
    private val context: Context
) :
    IGetHomePokemonUseCase {
    companion object {
        const val POKEMON_FIRST_CHUNK = 15
        const val POKEMON_OFFSET = 0
    }

    override suspend fun execute(): Flow<Unit> = flow {

        if (pokemonRepository.getPokemonsLocal().first().isEmpty()) {

            // Get the first chunk of pokemons
            val pokemonResult =
                pokemonRepository.getPokemonsAsync(POKEMON_FIRST_CHUNK, POKEMON_OFFSET)

            val pokemonItems = coroutineScope {
                pokemonResult.results.map { pokemon ->
                    async { pokemonRepository.getPokemonDetailAsync(pokemon.url) }
                }.awaitAll()
            }

            // Save the first chunk of pokemons to the local database
            val pokemonEntities = pokemonItems.map { pokemonItem ->
                pokemonItem.toEntity()
            }

            pokemonRepository.savePokemonsLocal(pokemonEntities)

            // Load the rest of the pokemons in the background
            pokedexLoadBatch(context)
            emit(Unit)
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun pokedexLoadBatch(context: Context) {
    val workRequest = OneTimeWorkRequestBuilder<PokemonWorker>().build()
    WorkManager.getInstance(context).enqueue(workRequest)
}
