package com.ba.pokedex.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ba.pokedex.base.BaseViewModel
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.usecases.IGetHomePokemonUseCase
import com.ba.pokedex.usecases.IGetPokemonUseCase
import com.ba.pokedex.utils.livedata.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonHomeViewModel(
    private val getHomePokemonUseCase: IGetHomePokemonUseCase,
    getPagedPokemonUseCase: IGetPokemonUseCase
) :
    BaseViewModel() {

    val getPokemonEvent = MutableLiveData<Event<Unit>>()

    val pokemonFlow: Flow<PagingData<PokemonItemUIModel>> = getPagedPokemonUseCase.execute()
        .cachedIn(viewModelScope)

    fun getFirst15Pokemons(context: Context) {
        viewModelScope.launch(contextProvider.getMainContext()) {
            showProgress()

            try {
                withContext(contextProvider.getIoContext()) {
                    getHomePokemonUseCase.execute(context)
                }
                getPokemonEvent.value = Event.Success(Unit)
            } catch (t: Throwable) {
                getPokemonEvent.value = Event.Failure(t)
            } finally {
                hideProgress()
            }
        }
    }

}