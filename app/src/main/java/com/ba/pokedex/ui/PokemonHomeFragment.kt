package com.ba.pokedex.ui

import androidx.fragment.app.viewModels
import com.ba.pokedex.R
import com.ba.pokedex.base.BaseFragment
import com.ba.pokedex.databinding.FragmentPokemonHomeBinding

class PokemonHomeFragment : BaseFragment<FragmentPokemonHomeBinding>() {

    private val viewModel: PokemonHomeViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_pokemon_home

    override fun getVariablesToBind(): Map<Int, Any> = emptyMap()

    override fun initObservers() {
    }


}