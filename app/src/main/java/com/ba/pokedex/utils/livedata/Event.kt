package com.ba.pokedex.utils.livedata

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
sealed class Event<T> {
    class Success<T>(val data: T) : Event<T>()
    class Failure<T>(val throwable: Throwable) : Event<T>()
}