package com.ba.pokedex.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ba.pokedex.R
import com.ba.pokedex.base.BaseFragment
import com.ba.pokedex.databinding.FragmentPokemonHomeBinding
import com.ba.pokedex.domain.PokemonResult
import com.ba.pokedex.utils.livedata.Event
import com.ba.pokedex.webservice.utils.getErrorMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonHomeFragment : BaseFragment<FragmentPokemonHomeBinding>() {

    private val pokemonObserver = Observer<Event<PokemonResult>> {
        onPokemonResult(it)
    }

    private val viewModel: PokemonHomeViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_pokemon_home

    override fun getVariablesToBind(): Map<Int, Any> = emptyMap()

    override fun initObservers() {
        viewModel.getPokemonEvent.observe(viewLifecycleOwner, pokemonObserver)
        viewModel.isLoadingEvent.observe(viewLifecycleOwner, isLoadingObserver)
    }


    override fun initView(inflater: LayoutInflater, container: ViewGroup?) {
        super.initView(inflater, container)
        viewModel.getPokemons()
    }

    private fun onPokemonResult(result: Event<PokemonResult>) {
        when (result) {
            is Event.Success -> {
                onPokemonSuccess(result.data)
            }

            is Event.Failure -> {
                onPokemonFailure(result.throwable)
            }
        }
    }

    private fun onPokemonFailure(throwable: Throwable) {
        showAlert(throwable.getErrorMessage(requireContext()))
    }

    private fun onPokemonSuccess(data: PokemonResult) {
        Log.d("PokemonHomeFragment", "onPokemonSuccess: $data")
    }

}