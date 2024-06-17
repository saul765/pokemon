package com.ba.pokedex.base

import com.ba.pokedex.coroutines.ICoroutineContextProvider
import com.ba.pokedex.di.DatabaseModule
import com.ba.pokedex.di.LocalStorageModule
import com.ba.pokedex.di.RepositoriesModule
import com.ba.pokedex.di.UseCasesModule
import com.ba.pokedex.di.ViewModelsModule
import com.ba.pokedex.di.WebServiceModule
import com.ba.pokedex.utils.notifications.INotificationService
import com.ba.pokedex.utils.permissions.IPermissionService
import com.ba.pokedex.webservice.apis.IPokemonApi
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock

object TestModules : KoinTest {

    val modules = listOf(
        RepositoriesModule.module,
        LocalStorageModule.module,
        ViewModelsModule.module,
        WebServiceModule.module,
        DatabaseModule.module,
        UseCasesModule.module
    ) + module {


        //Coroutine context provider
        single<ICoroutineContextProvider> { TestCoroutineContextProvider() }

        // Notification Service
        single<INotificationService> { declareMock() }

        // Permission Service
        single<IPermissionService> { declareMock() }

        //Apis
        single<IPokemonApi> { declareMock() }


    }


}