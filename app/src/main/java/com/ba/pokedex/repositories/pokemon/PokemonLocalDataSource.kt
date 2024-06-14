package com.ba.pokedex.repositories.pokemon

import com.ba.pokedex.database.dao.IPokemonDao
import com.ba.pokedex.database.entity.PokemonEntity

class PokemonLocalDataSource(private val pokemonDAO: IPokemonDao) : IPokemonDataSource.Local {
    override suspend fun getPokemons(): List<PokemonEntity> = pokemonDAO.findAll()

    override suspend fun savePokemons(pokemons: List<PokemonEntity>) = pokemonDAO.saveAll(pokemons)

    override suspend fun getPokemonById(id: String): PokemonEntity = pokemonDAO.findPokemonById(id)
}