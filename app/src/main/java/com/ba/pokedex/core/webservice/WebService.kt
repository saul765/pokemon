package com.ba.pokedex.core.webservice

import android.content.Context
import com.ba.pokedex.R
import com.ba.pokedex.core.webservice.apis.IPokemonApi
import com.ba.pokedex.core.webservice.utils.network.IOkHttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebService : KoinComponent {

    private val context by inject<Context>()

    private val okHttpClient by inject<IOkHttpClient>()

    private val gsonConverterFactory by inject<GsonConverterFactory>()


    fun createPokemonApi(): IPokemonApi {
        val client = okHttpClient.create()
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.pokemon_base_url))
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
        return retrofit.create(IPokemonApi::class.java)
    }

}