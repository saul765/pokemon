package com.ba.pokedex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ba.pokedex.base.BaseViewModel
import com.ba.pokedex.domain.PokemonResult
import com.ba.pokedex.usecases.IGetPokemonUseCase
import com.ba.pokedex.utils.livedata.Event
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonHomeViewModel(private val getPokemonUseCase: IGetPokemonUseCase) : BaseViewModel() {


    val getPokemonEvent = MutableLiveData<Event<PokemonResult>>()

    fun getPokemons() {
        viewModelScope.launch(contextProvider.getMainContext()) {
            showProgress()

            try {
                val result = withContext(contextProvider.getIoContext()) {
                    getPokemonUseCase.execute()
                }

                getPokemonEvent.value = Event.Success(result)
            } catch (t: Throwable) {
                getPokemonEvent.value = Event.Failure(t)
            } finally {
                hideProgress()
            }
        }
    }

}