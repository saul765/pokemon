package com.ba.pokedex.repositories.pokemon

import com.ba.pokedex.webservice.dto.responses.PokemonResultDTO

class PokemonLocalDataSource : IPokemonDataSource.Local {
    override suspend fun getPokemons(limit: Int, offset: Int): PokemonResultDTO {
        throw UnsupportedOperationException("Local data source is not implemented")
    }
}