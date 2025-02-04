package com.ba.pokedex.core.pokemon

import androidx.paging.PagingSource
import com.ba.pokedex.core.database.dao.IPokemonDao
import com.ba.pokedex.core.database.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

class PokemonLocalDataSource(private val pokemonDAO: IPokemonDao) : IPokemonDataSource.Local {

    override fun getPokemons(): Flow<List<PokemonEntity>> = pokemonDAO.findAll()

    override fun getPokemonsPaged(): PagingSource<Int, PokemonEntity> =
        pokemonDAO.findAllPaged()

    override suspend fun savePokemons(pokemons: List<PokemonEntity>) = pokemonDAO.saveAll(pokemons)

    override suspend fun getPokemonById(id: Int): PokemonEntity = pokemonDAO.findPokemonById(id)

    override suspend fun getTotalNumberOfPokemons(): Int = pokemonDAO.getTotalNumberOfPokemons()
}