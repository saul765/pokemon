package com.ba.pokedex.ui.home.state

import androidx.compose.runtime.Stable
import com.ba.pokedex.core.domain.uimodel.PokemonItemUIModel

@Stable
data class HomeUiState(val pokemons: List<PokemonItemUIModel> = listOf())