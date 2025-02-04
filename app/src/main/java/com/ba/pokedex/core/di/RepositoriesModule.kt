package com.ba.pokedex.core.di


import com.ba.pokedex.core.pokemon.IPokemonDataSource
import com.ba.pokedex.core.pokemon.PokemonLocalDataSource
import com.ba.pokedex.core.pokemon.PokemonRemoteDataSource
import com.ba.pokedex.core.pokemon.PokemonRepository
import org.koin.dsl.module

object RepositoriesModule {

    val module = module {

        //Pokemon Repository

        single<IPokemonDataSource.Repository> { PokemonRepository(get(), get()) }

        single<IPokemonDataSource.Remote> { PokemonRemoteDataSource(get()) }

        single<IPokemonDataSource.Local> { PokemonLocalDataSource(get()) }

    }
}