package com.ba.pokedex.base

import com.ba.pokedex.coroutines.ICoroutineContextProvider
import kotlinx.coroutines.Dispatchers

class TestCoroutineContextProvider : ICoroutineContextProvider {

    override fun getMainContext() = Dispatchers.Unconfined
    override fun getIoContext() = Dispatchers.Unconfined
}