package com.ba.pokedex.coroutines

import kotlin.coroutines.CoroutineContext

interface ICoroutineContextProvider {
    fun getMainContext(): CoroutineContext
    fun getIoContext(): CoroutineContext
}