package com.ba.pokedex.core.coroutines

import kotlin.coroutines.CoroutineContext

interface ICoroutineContextProvider {
    fun getMainContext(): CoroutineContext
    fun getIoContext(): CoroutineContext
}