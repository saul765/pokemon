package com.ba.pokedex.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.dsl.module

object LocalStorageModule {

    private const val PREF_NAME = "pokemon_data_preferences"

    val module = module {

        single { provideSharedPref(get()) }
    }

    private fun provideSharedPref(app: Application): SharedPreferences {
        return app.applicationContext.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
    }
}