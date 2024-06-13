package com.ba.pokedex.di


import com.ba.pokedex.ui.PokemonHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object ViewModelsModule {
    val module = module {

        viewModelOf(::PokemonHomeViewModel)

    }
}