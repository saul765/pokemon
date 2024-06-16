package com.ba.pokedex.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ba.pokedex.database.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IPokemonDao {

    @Insert
    suspend fun saveAll(entities: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE id = :id")
    suspend fun findPokemonById(id: Int): PokemonEntity

    @Query("SELECT * FROM pokemons")
    suspend fun findAll(): List<PokemonEntity>

    @Query("SELECT * FROM pokemons ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun findAllPaged(limit: Int, offset: Int): List<PokemonEntity>

    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    fun findAllPaged2(): PagingSource<Int, PokemonEntity>

    @Query("SELECT COUNT(*) FROM pokemons")
    suspend fun getTotalNumberOfPokemons(): Int


}