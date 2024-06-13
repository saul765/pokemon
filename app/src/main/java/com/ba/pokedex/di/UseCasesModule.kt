package com.ba.pokedex.di

import com.ba.pokedex.usecases.GetPokemonUseCase
import com.ba.pokedex.usecases.IGetPokemonUseCase
import org.koin.dsl.module

object UseCasesModule {
    val module = module {

        //  Get pokemons use case
        single<IGetPokemonUseCase> { GetPokemonUseCase(get()) }

    }
}