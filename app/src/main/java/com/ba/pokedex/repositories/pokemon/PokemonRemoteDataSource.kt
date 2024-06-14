package com.ba.pokedex.repositories.pokemon

import com.ba.pokedex.webservice.apis.IPokemonApi
import com.ba.pokedex.webservice.dto.responses.PokemonItemDTO
import com.ba.pokedex.webservice.dto.responses.PokemonResultDTO

class PokemonRemoteDataSource(private val pokemonApi: IPokemonApi) : IPokemonDataSource.Remote {

    override suspend fun getPokemons(limit: Int, offset: Int): PokemonResultDTO =
        pokemonApi.getPokemons(limit, offset)

    override suspend fun getPokemonDetail(pokemonUrl: String): PokemonItemDTO =
        pokemonApi.getPokemonDetail(pokemonUrl)

}