package com.ba.pokedex.di

import com.ba.pokedex.usecases.GetHomePokemonUseCase
import com.ba.pokedex.usecases.IGetHomePokemonUseCase
import org.koin.dsl.module

object UseCasesModule {
    val module = module {

        //  Get pokemons use case
        single<IGetHomePokemonUseCase> { GetHomePokemonUseCase(get()) }

    }
}