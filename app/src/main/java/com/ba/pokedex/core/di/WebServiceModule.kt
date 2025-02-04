package com.ba.pokedex.core.di


import com.ba.pokedex.core.webservice.WebService
import com.ba.pokedex.core.webservice.apis.IPokemonApi
import com.ba.pokedex.core.webservice.utils.network.IOkHttpClient
import com.ba.pokedex.core.webservice.utils.network.LoggingInterceptor
import com.ba.pokedex.core.webservice.utils.network.OkHttpClient
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

object WebServiceModule {

    val module = module {

        single<IOkHttpClient> { OkHttpClient(get()) }

        single { LoggingInterceptor }

        single<IPokemonApi> { WebService.createPokemonApi() }

        single<GsonConverterFactory> { GsonConverterFactory.create() }
    }


}