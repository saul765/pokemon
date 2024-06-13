package com.ba.pokedex.webservice.utils.network

import okhttp3.OkHttpClient

interface IOkHttpClient {
    fun create(): OkHttpClient
}