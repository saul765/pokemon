package com.ba.pokedex.di

import androidx.room.Room
import com.ba.pokedex.data.POKEMON_DATABASE_NAME
import com.ba.pokedex.database.PokemonDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object DatabaseModule {

    val module = module {

        single {
            Room.databaseBuilder(
                androidApplication(),
                PokemonDatabase::class.java,
                POKEMON_DATABASE_NAME
            ).build()
        }

        single {
            get<PokemonDatabase>().pokemonDao()
        }
    }
}