package com.ba.pokedex.webservice

import android.content.Context
import com.ba.pokedex.R
import com.ba.pokedex.webservice.apis.IPokemonApi
import com.ba.pokedex.webservice.utils.network.IOkHttpClient
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
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(IPokemonApi::class.java)
    }

}