package com.ba.pokedex.repositories.pokemon

import com.ba.pokedex.database.entity.PokemonEntity
import com.ba.pokedex.domain.PokemonItem
import com.ba.pokedex.domain.PokemonResult
import com.ba.pokedex.webservice.dto.responses.toDomain

class PokemonRepository(
    private val remoteDataSource: IPokemonDataSource.Remote,
    private val localDataSource: IPokemonDataSource.Local
) : IPokemonDataSource.Repository {
    override suspend fun getPokemonsAsync(limit: Int, offset: Int): PokemonResult =
        remoteDataSource.getPokemons(limit, offset).toDomain()

    override suspend fun getPokemonsLocal(): List<PokemonEntity> = localDataSource.getPokemons()

    override suspend fun savePokemonsLocal(pokemons: List<PokemonEntity>) =
        localDataSource.savePokemons(pokemons)

    override suspend fun getPokemonByIdLocal(id: String): PokemonEntity =
        localDataSource.getPokemonById(id)

    override suspend fun getPokemonDetailAsync(pokemonUrl: String): PokemonItem =
        remoteDataSource.getPokemonDetail(pokemonUrl).toDomain()

}