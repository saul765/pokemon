package com.ba.pokedex.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ba.pokedex.database.entity.PokemonEntity

@Dao
interface IPokemonDao {

    @Insert
    suspend fun saveAll(entities: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE id = :id")
    suspend fun findPokemonById(id: Int): PokemonEntity

    @Query("SELECT * FROM pokemons")
    suspend fun findAll(): List<PokemonEntity>

    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    fun findAllPaged(): PagingSource<Int, PokemonEntity>

    @Query("SELECT COUNT(*) FROM pokemons")
    suspend fun getTotalNumberOfPokemons(): Int


}