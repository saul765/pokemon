package com.ba.pokedex.repositories.pokemon

import com.ba.pokedex.database.entity.PokemonEntity
import com.ba.pokedex.domain.PokemonItem
import com.ba.pokedex.domain.PokemonResult
import com.ba.pokedex.webservice.dto.responses.PokemonItemDTO
import com.ba.pokedex.webservice.dto.responses.PokemonResultDTO

interface IPokemonDataSource {

    interface Remote {
        suspend fun getPokemons(limit: Int, offset: Int): PokemonResultDTO

        suspend fun getPokemonDetail(pokemonUrl: String): PokemonItemDTO
    }

    interface Local {
        suspend fun getPokemons(): List<PokemonEntity>

        suspend fun savePokemons(pokemons: List<PokemonEntity>)

        suspend fun getPokemonById(id: Int): PokemonEntity

        suspend fun getTotalNumberOfPokemons(): Int
    }

    interface Repository {
        suspend fun getPokemonsAsync(limit: Int, offset: Int): PokemonResult

        suspend fun getPokemonsLocal(): List<PokemonEntity>

        suspend fun savePokemonsLocal(pokemons: List<PokemonEntity>)

        suspend fun getPokemonByIdLocal(id: Int): PokemonEntity

        suspend fun getPokemonDetailAsync(pokemonUrl: String): PokemonItem

        suspend fun getTotalNumberOfPokemonsLocal(): Int
    }
}