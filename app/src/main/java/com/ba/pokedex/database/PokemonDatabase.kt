package com.ba.pokedex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ba.pokedex.database.dao.IPokemonDao
import com.ba.pokedex.database.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): IPokemonDao

}