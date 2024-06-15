package com.ba.pokedex.base

import com.ba.pokedex.di.DatabaseModule
import com.ba.pokedex.di.LocalStorageModule
import com.ba.pokedex.di.RepositoriesModule
import com.ba.pokedex.di.ViewModelsModule
import com.ba.pokedex.di.WebServiceModule
import org.koin.test.KoinTest

object TestModules : KoinTest {

    val modules = listOf(
        RepositoriesModule.module,
        LocalStorageModule.module,
        ViewModelsModule.module,
        WebServiceModule.module,
        DatabaseModule.module
    )

}