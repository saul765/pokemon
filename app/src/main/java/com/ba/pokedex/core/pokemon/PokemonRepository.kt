package com.ba.pokedex.core.pokemon

import androidx.paging.PagingSource
import com.ba.pokedex.core.database.entity.PokemonEntity
import com.ba.pokedex.core.domain.PokemonItem
import com.ba.pokedex.core.domain.PokemonResult
import com.ba.pokedex.core.webservice.dto.responses.toDomain
import kotlinx.coroutines.flow.Flow

class PokemonRepository(
    private val remoteDataSource: IPokemonDataSource.Remote,
    private val localDataSource: IPokemonDataSource.Local
) : IPokemonDataSource.Repository {
    override suspend fun getPokemonsAsync(limit: Int, offset: Int): PokemonResult =
        remoteDataSource.getPokemons(limit, offset).toDomain()

    override  fun getPokemonsLocal(): Flow<List<PokemonEntity>> =
        localDataSource.getPokemons()

    override fun getPokemonsPaged(): PagingSource<Int, PokemonEntity> =
        localDataSource.getPokemonsPaged()

    override suspend fun savePokemonsLocal(pokemons: List<PokemonEntity>) =
        localDataSource.savePokemons(pokemons)

    override suspend fun getPokemonByIdLocal(id: Int): PokemonEntity =
        localDataSource.getPokemonById(id)

    override suspend fun getPokemonDetailAsync(pokemonUrl: String): PokemonItem =
        remoteDataSource.getPokemonDetail(pokemonUrl).toDomain()

    override suspend fun getTotalNumberOfPokemonsLocal(): Int =
        localDataSource.getTotalNumberOfPokemons()

}