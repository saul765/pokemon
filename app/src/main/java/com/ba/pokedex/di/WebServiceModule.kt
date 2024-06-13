package com.ba.pokedex.di


import com.ba.pokedex.webservice.WebService
import com.ba.pokedex.webservice.apis.IPokemonApi
import com.ba.pokedex.webservice.utils.network.IOkHttpClient
import com.ba.pokedex.webservice.utils.network.LoggingInterceptor
import com.ba.pokedex.webservice.utils.network.OkHttpClient
import org.koin.dsl.module

object WebServiceModule {

    val module = module {

        single<IOkHttpClient> { OkHttpClient(get()) }

        single { LoggingInterceptor }

        single<IPokemonApi> { WebService.createPokemonApi() }
    }


}