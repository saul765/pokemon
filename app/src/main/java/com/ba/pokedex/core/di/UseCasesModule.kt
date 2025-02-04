package com.ba.pokedex.core.di

import com.ba.pokedex.core.usecases.GetHomePokemonUseCase
import com.ba.pokedex.core.usecases.GetPokemonUseCase
import com.ba.pokedex.core.usecases.IGetHomePokemonUseCase
import com.ba.pokedex.core.usecases.IGetPokemonUseCase
import com.ba.pokedex.core.usecases.IWorkerPokemonUseCase
import com.ba.pokedex.core.usecases.WorkerPokemonUseCase
import org.koin.dsl.module

object UseCasesModule {
    val module = module {

        //  Get pokemons use case
        single<IGetHomePokemonUseCase> {
            GetHomePokemonUseCase(
                get(), get()
            )
        }

        // Worker use case
        single<IWorkerPokemonUseCase> { WorkerPokemonUseCase(get()) }

        single<IGetPokemonUseCase> { GetPokemonUseCase(get()) }

        single<IGetPokemonUseCase> { GetPokemonUseCase(get()) }

    }
}