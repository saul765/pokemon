package com.ba.pokedex.di


import com.ba.pokedex.repositories.pokemon.IPokemonDataSource
import com.ba.pokedex.repositories.pokemon.PokemonLocalDataSource
import com.ba.pokedex.repositories.pokemon.PokemonRemoteDataSource
import com.ba.pokedex.repositories.pokemon.PokemonRepository
import org.koin.dsl.module

object RepositoriesModule {

    val module = module {

        //Pokemon Repository

        single<IPokemonDataSource.Repository> { PokemonRepository(get(), get()) }
        single<IPokemonDataSource.Remote> { PokemonRemoteDataSource(get()) }
        single<IPokemonDataSource.Local> { PokemonLocalDataSource() }


    }
}