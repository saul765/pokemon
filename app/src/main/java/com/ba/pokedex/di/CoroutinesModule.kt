package com.ba.pokedex.di

import com.ba.pokedex.coroutines.CoroutineContextProvider
import com.ba.pokedex.coroutines.ICoroutineContextProvider
import org.koin.dsl.module

object CoroutinesModule {

    val module = module {

        single<ICoroutineContextProvider> { CoroutineContextProvider() }
    }
}