package com.ba.pokedex.core.pokemon

import androidx.paging.PagingSource
import com.ba.pokedex.core.database.entity.PokemonEntity
import com.ba.pokedex.core.domain.PokemonResult
import com.ba.pokedex.core.webservice.dto.responses.PokemonItemDTO
import com.ba.pokedex.core.webservice.dto.responses.PokemonResultDTO
import kotlinx.coroutines.flow.Flow

interface IPokemonDataSource {

    interface Remote {
        suspend fun getPokemons(limit: Int, offset: Int): PokemonResultDTO

        suspend fun getPokemonDetail(pokemonUrl: String): PokemonItemDTO
    }

    interface Local {
        fun getPokemons(): Flow<List<PokemonEntity>>

        fun getPokemonsPaged(): PagingSource<Int, PokemonEntity>

        suspend fun savePokemons(pokemons: List<PokemonEntity>)

        suspend fun getPokemonById(id: Int): PokemonEntity

        suspend fun getTotalNumberOfPokemons(): Int
    }

    interface Repository {
        suspend fun getPokemonsAsync(limit: Int, offset: Int): PokemonResult

        fun getPokemonsLocal(): Flow<List<PokemonEntity>>

        fun getPokemonsPaged(): PagingSource<Int, PokemonEntity>

        suspend fun savePokemonsLocal(pokemons: List<PokemonEntity>)

        suspend fun getPokemonByIdLocal(id: Int): PokemonEntity

        suspend fun getPokemonDetailAsync(pokemonUrl: String): com.ba.pokedex.core.domain.PokemonItem

        suspend fun getTotalNumberOfPokemonsLocal(): Int
    }
}