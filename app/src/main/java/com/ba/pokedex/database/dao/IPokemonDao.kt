package com.ba.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ba.pokedex.database.entity.PokemonEntity

@Dao
interface IPokemonDao {

    @Insert
    suspend fun saveAll(entities: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE id = :id")
    suspend fun findPokemonById(id: String): PokemonEntity

    @Query("SELECT * FROM pokemons")
    suspend fun findAll(): List<PokemonEntity>


}