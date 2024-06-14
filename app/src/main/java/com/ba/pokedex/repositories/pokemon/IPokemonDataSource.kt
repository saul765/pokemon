package com.ba.pokedex.repositories.pokemon

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
        suspend fun getPokemons(limit: Int, offset: Int): PokemonResultDTO
    }

    interface Repository {
        suspend fun getPokemonsAsync(limit: Int, offset: Int): PokemonResult

        suspend fun getPokemonsLocal(limit: Int, offset: Int): PokemonResult

        suspend fun getPokemonDetailAsync(pokemonUrl: String): PokemonItem
    }
}