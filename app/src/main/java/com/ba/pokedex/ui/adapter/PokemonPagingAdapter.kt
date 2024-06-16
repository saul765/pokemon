package com.ba.pokedex.ui.adapter

import com.ba.pokedex.BR
import com.ba.pokedex.R
import com.ba.pokedex.base.BasePagedRecyclerViewAdapter
import com.ba.pokedex.base.BaseRecyclerViewAdapter
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel


class PokemonPagingAdapter(private val onItemClickListener: (PokemonItemUIModel) -> Unit) :
    BasePagedRecyclerViewAdapter<PokemonItemUIModel>(
        null, PokemonPaginationItemCallback()
    ) {
    override fun itemLayoutId(): Int = R.layout.item_pokemon

    override fun itemToBindId(): Int = BR.pokemonItem

    override fun onBindViewHolder(holder: BaseRecyclerViewAdapter.BaseViewHolder, position: Int) {
        getItem(position)?.let {
            val binder = PokemonItemViewBinder(it, onItemClickListener)
            holder.bind(binder)
        }
    }
}