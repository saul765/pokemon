package com.ba.pokedex.ui.home

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.ba.pokedex.R
import com.ba.pokedex.core.base.BaseFragment
import com.ba.pokedex.databinding.FragmentPokemonHomeBinding

class HomeFragment : BaseFragment<FragmentPokemonHomeBinding>() {


    override fun getLayoutId(): Int = R.layout.fragment_pokemon_home

    override fun getVariablesToBind(): Map<Int, Any> = emptyMap()

    override fun initObservers() {}

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun initView(inflater: LayoutInflater, container: ViewGroup?) {
        super.initView(inflater, container)

        initComposable(dataBinding.composeView) {
            HomeScreen()
        }
    }

}