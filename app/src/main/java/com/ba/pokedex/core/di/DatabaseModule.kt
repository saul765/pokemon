package com.ba.pokedex.core.di

import androidx.room.Room
import com.ba.pokedex.core.data.POKEMON_DATABASE_NAME
import com.ba.pokedex.core.database.PokemonDatabase
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