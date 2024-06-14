package com.ba.pokedex.ui.adapter

import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.utils.FormatUtils
import com.ba.pokedex.utils.capitalize

class PokemonItemViewBinder(
    private val pokemonItem: PokemonItemUIModel,
    private val pokemonClickListener: (PokemonItemUIModel) -> Unit
) {

    fun getPokemonName() = pokemonItem.name.capitalize()

    fun getPokemonImageUrl() = pokemonItem.imageUrl

    fun onPokemonClick() {
        pokemonClickListener(pokemonItem)
    }

    fun getPokemonId() = FormatUtils.formatPokemonNumber(pokemonItem.id)

}