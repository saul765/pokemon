package com.ba.pokedex.ui.adapter

import com.ba.pokedex.BR
import com.ba.pokedex.R
import com.ba.pokedex.base.BaseRecyclerViewAdapter
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel

class PokemonAdapter(
    private val data: List<PokemonItemUIModel>,
    private val itemClickListener: (PokemonItemUIModel) -> Unit
) : BaseRecyclerViewAdapter(data) {

    override fun getItemCount(): Int = data.size
    override fun itemLayoutId(): Int = R.layout.item_pokemon

    override fun itemToBindId(): Int = BR.pokemonItem

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val binder = PokemonItemViewBinder(data[position], itemClickListener)
        holder.bind(binder)
    }

}