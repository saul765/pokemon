package com.ba.pokedex.di

import com.ba.pokedex.utils.notifications.INotificationService
import com.ba.pokedex.utils.notifications.NotificationService
import com.ba.pokedex.utils.permissions.IPermissionService
import com.ba.pokedex.utils.permissions.PermissionService
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

        single<INotificationService> { NotificationService() }

        single<IPermissionService> { PermissionService() }

    }

    private val databaseModule = listOf(
        DatabaseModule.module
    )

    private val viewModelsModules = listOf(
        ViewModelsModule.module
    )

    private val useCasesModule = listOf(
        UseCasesModule.module
    )

    val modules =
        coreModules + myModule + viewModelsModules + useCasesModule + databaseModule
}