package com.ba.pokedex.core.di


import com.ba.pokedex.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object ViewModelsModule {
    val module = module {

        viewModelOf(::HomeViewModel)

    }
}