package com.ba.pokedex.di

import org.koin.dsl.module


object AppModule {

    private val coreModules =
        listOf(
            CoroutinesModule.module,
            RepositoriesModule.module,
            LocalStorageModule.module,
            WebServiceModule.module
        )

    private val myModule = module {


    }

    private val viewModelsModules = listOf(
        ViewModelsModule.module
    )

    private val useCasesModule = listOf(
        UseCasesModule.module
    )

    val modules =
        coreModules + myModule + viewModelsModules + useCasesModule
}