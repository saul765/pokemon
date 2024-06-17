package com.ba.pokedex.ui

import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ba.pokedex.R
import com.ba.pokedex.base.BaseFragment
import com.ba.pokedex.databinding.FragmentPokemonHomeBinding
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

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val viewModel: PokemonHomeViewModel by viewModel()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val permissionService: IPermissionService by inject()

    private val pokemonObserver = Observer<Event<Unit>> {
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
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onPokemonResult(result: Event<Unit>) {
        when (result) {
            is Event.Success -> {
                onPokemonSuccess()
            }

            is Event.Failure -> {
                onPokemonFailure(result.throwable)
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onPokemonFailure(throwable: Throwable) {
        showAlert(throwable.getErrorMessage(requireContext()))
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onPokemonSuccess() {
        loadAllPokemons()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun checkPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.getFirst15Pokemons(requireContext())
            }

            else -> {
                permissionService.runWithPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS,
                    onRationale = {
                    },
                    onGranted = {
                        viewModel.getFirst15Pokemons(requireContext())
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

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun loadAllPokemons() {
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