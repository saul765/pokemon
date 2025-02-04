package com.ba.pokedex.ui.home

import androidx.lifecycle.viewModelScope
import com.ba.pokedex.core.base.viewmodel.BaseViewModel
import com.ba.pokedex.core.data.UIStateStatus
import com.ba.pokedex.core.database.entity.toUIModel
import com.ba.pokedex.core.usecases.IGetHomePokemonUseCase
import com.ba.pokedex.core.usecases.IGetPokemonUseCase
import com.ba.pokedex.ui.home.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHomePokemonUseCase: IGetHomePokemonUseCase,
    private val getPokemonLocal: IGetPokemonUseCase
) :
    BaseViewModel() {

    private val _pokemonUiState = MutableStateFlow<UIStateStatus<HomeUiState>>(
        UIStateStatus.Empty(
            HomeUiState()
        )
    )

    init {
        getPokemons()
    }

    val pokemonUiState = _pokemonUiState.asStateFlow()

    private fun getPokemons() {
        viewModelScope.launch {
            getPokemonLocal.execute()
                .flowOn(contextProvider.getIoContext())
                .map { pokemons -> pokemons.map { it.toUIModel() } }
                .catch { e -> showError(e) }
                .collect { pokemons ->
                    if (pokemons.isEmpty()) {
                        _pokemonUiState.update { UIStateStatus.Empty(HomeUiState()) }
                    } else {
                        _pokemonUiState.update { UIStateStatus.Success(HomeUiState(pokemons)) }
                    }
                }
        }
    }


    fun getFirst15Pokemons() {
        viewModelScope.launch(contextProvider.getMainContext()) {
            getHomePokemonUseCase.execute()
                .flowOn(contextProvider.getIoContext())
                .onStart { showLoading() }
                .onCompletion { hideLoading() }
                .catch { e -> showError(e) }
                .collect()
        }
    }

}