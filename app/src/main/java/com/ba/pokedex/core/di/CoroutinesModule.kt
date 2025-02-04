package com.ba.pokedex.core.di

import com.ba.pokedex.core.coroutines.CoroutineContextProvider
import com.ba.pokedex.core.coroutines.ICoroutineContextProvider
import org.koin.dsl.module

object CoroutinesModule {

    val module = module {

        single<ICoroutineContextProvider> { CoroutineContextProvider() }
    }
}