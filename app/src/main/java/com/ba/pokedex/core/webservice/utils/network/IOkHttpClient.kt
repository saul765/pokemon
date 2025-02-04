package com.ba.pokedex.core.webservice.utils.network

import okhttp3.OkHttpClient

interface IOkHttpClient {
    fun create(): OkHttpClient
}