package com.ba.pokedex.ui.home

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ba.pokedex.R
import com.ba.pokedex.core.base.screen.BaseScreen
import com.ba.pokedex.core.base.state.rememberDialogState
import com.ba.pokedex.core.data.ZERO_INTEGER
import com.ba.pokedex.core.design_system.BaseLottieAnimation
import com.ba.pokedex.core.design_system.BasePermission
import com.ba.pokedex.core.utils.extensions.goToAppSettings
import com.ba.pokedex.ui.home.state.HomeUiState
import com.ba.pokedex.ui.home.views.HomeView
import com.ba.pokedex.ui.home.views.PokemonTopBar
import com.ba.pokedex.core.data.UIStateStatus
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.flow.filterIsInstance


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(onSearchClick: () -> Unit = {}) {

    var notificationCount by remember { mutableIntStateOf(ZERO_INTEGER) }

    val context = LocalContext.current

    var permissionRequestExecuted by remember { mutableStateOf(false) }


    BaseScreen<HomeViewModel>(topBar = {
        PokemonTopBar(onSearchClick = onSearchClick, notificationCount = notificationCount)
    }) { viewModel ->

        val dialogState = rememberDialogState(false)

        val permissionState = rememberMultiplePermissionsState(
            listOf(android.Manifest.permission.POST_NOTIFICATIONS)
        ) { result ->
            val allGranted = result.values.all { granted -> granted }
            if (allGranted && !permissionRequestExecuted) {
                permissionRequestExecuted = true
                viewModel.getFirst15Pokemons()
            } else {
                dialogState.openDialog()
            }
        }

        val uiState by viewModel.pokemonUiState.collectAsStateWithLifecycle()

        LaunchedEffect(permissionState.allPermissionsGranted) {
            if (!permissionRequestExecuted) {
                permissionState.launchMultiplePermissionRequest()
            }
        }

        LaunchedEffect(Unit) {
            snapshotFlow { uiState }
                .filterIsInstance<UIStateStatus.Success<HomeUiState>>()
                .collect { uiState ->
                    notificationCount = uiState.data.pokemons.size
                }
        }

        if (dialogState.isDialogVisible) {
            BasePermission(
                dialogState = dialogState,
                state = permissionState,
                rationale = stringResource(R.string.pokemon_rationale_permission),
                goToAppSettings = { context.goToAppSettings() },
                onGranted = { viewModel.getFirst15Pokemons() },
                mustRequire = true
            )
        }

        when (val state = uiState) {
            is UIStateStatus.Success -> {
                HomeView(uiState = state.data, onCardClicked = { pokemonItemUIModel ->
                    Toast.makeText(
                        context,
                        pokemonItemUIModel.name.capitalize(),
                        Toast.LENGTH_SHORT
                    ).show()
                })
            }

            else -> {
                BaseLottieAnimation(res = R.raw.pokeball_anim, modifier = Modifier.size(200.dp))
            }
        }

    }
}
