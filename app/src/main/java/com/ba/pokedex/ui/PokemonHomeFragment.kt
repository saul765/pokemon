package com.ba.pokedex.ui

import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ba.pokedex.R
import com.ba.pokedex.base.BaseFragment
import com.ba.pokedex.databinding.FragmentPokemonHomeBinding
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.ui.adapter.MainLoadStateAdapter
import com.ba.pokedex.ui.adapter.PokemonPagingAdapter
import com.ba.pokedex.utils.livedata.Event
import com.ba.pokedex.utils.permissions.IPermissionService
import com.ba.pokedex.webservice.utils.getErrorMessage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.inject

class PokemonHomeFragment : BaseFragment<FragmentPokemonHomeBinding>() {

    private val viewModel: PokemonHomeViewModel by viewModel()

    private val permissionService: IPermissionService by inject()

    private val pokemonObserver = Observer<Event<List<PokemonItemUIModel>>> {
        onPokemonResult(it)
    }

    override fun getLayoutId(): Int = R.layout.fragment_pokemon_home

    override fun getVariablesToBind(): Map<Int, Any> = emptyMap()

    override fun initObservers() {
        viewModel.getPokemonEvent.observe(viewLifecycleOwner, pokemonObserver)
        viewModel.isLoadingEvent.observe(viewLifecycleOwner, isLoadingObserver)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?) {
        super.initView(inflater, container)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkPermissions()
        } else {
            viewModel.getFirst15Pokemons(requireContext())
            // loadAllPokemons()
        }
    }

    private fun onPokemonResult(result: Event<List<PokemonItemUIModel>>) {
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

    private fun onPokemonSuccess(data: List<PokemonItemUIModel>) {
        /* with(dataBinding.rvPokemon) {
             adapter = PokemonAdapter(data) {
                 Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
             }
         } */
        loadAllPokemons()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.getFirst15Pokemons(requireContext())
                //loadAllPokemons()
            }

            else -> {
                permissionService.runWithPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS,
                    onRationale = {
                    },
                    onGranted = {
                        viewModel.getFirst15Pokemons(requireContext())

                        //loadAllPokemons()
                    },
                    onDenied = {
                        showAlertWithTitle(
                            getString(R.string.pokemon_worker_error_notification_title),
                            getString(R.string.pokemon_worker_error_notification_body)
                        )
                    }
                )
            }
        }
    }

    private fun loadAllPokemons() {
        val adapter = PokemonPagingAdapter {
        }
        dataBinding.rvPokemon.adapter = adapter.withLoadStateFooter(
            MainLoadStateAdapter()
        )

        lifecycleScope.launch {
            viewModel.pokemonFlow.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}