package com.ba.pokedex.repositories.pokemon

import androidx.paging.PagingSource
import com.ba.pokedex.database.dao.IPokemonDao
import com.ba.pokedex.database.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

class PokemonLocalDataSource(private val pokemonDAO: IPokemonDao) : IPokemonDataSource.Local {
    override suspend fun getPokemons(): List<PokemonEntity> = pokemonDAO.findAll()
    override suspend fun getPokemonsPaged(limit: Int, offset: Int): List<PokemonEntity> =
        pokemonDAO.findAllPaged(limit, offset)

    override  fun getPokemonsPaged2():PagingSource<Int, PokemonEntity> =
        pokemonDAO.findAllPaged2()

    override suspend fun savePokemons(pokemons: List<PokemonEntity>) = pokemonDAO.saveAll(pokemons)

    override suspend fun getPokemonById(id: Int): PokemonEntity = pokemonDAO.findPokemonById(id)

    override suspend fun getTotalNumberOfPokemons(): Int = pokemonDAO.getTotalNumberOfPokemons()
}