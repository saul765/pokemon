package com.ba.pokedex.webservice.apis

import com.ba.pokedex.webservice.dto.responses.PokemonResultDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface IPokemonApi {

    companion object {
        //Params
        const val LIMIT_PARAM = "limit"
        const val OFFSET_PARAM = "offset"

        //Endpoints
        const val GET_POKEMONS = "pokemon"

    }

    @GET(GET_POKEMONS)
    suspend fun getPokemons(
        @Query(LIMIT_PARAM) limit: Int,
        @Query(OFFSET_PARAM) offset: Int
    ): PokemonResultDTO
}