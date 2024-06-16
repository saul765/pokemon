package com.ba.pokedex.ui.adapter

import com.ba.pokedex.base.BasePagedRecyclerViewAdapter
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel

class PokemonPaginationItemCallback :
    BasePagedRecyclerViewAdapter.BaseItemCallback<PokemonItemUIModel>() {
    override fun areItemsTheSame(
        oldItem: PokemonItemUIModel,
        newItem: PokemonItemUIModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: PokemonItemUIModel,
        newItem: PokemonItemUIModel
    ): Boolean = oldItem == newItem
}