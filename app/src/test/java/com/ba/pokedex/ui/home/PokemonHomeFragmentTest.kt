package com.ba.pokedex.ui.home

import androidx.fragment.app.testing.launchFragment
import com.ba.pokedex.base.BaseUnitTestNoKoin
import com.ba.pokedex.ui.PokemonHomeFragment
import com.ba.pokedex.ui.PokemonHomeViewModel
import com.ba.pokedex.utils.livedata.Event
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PokemonHomeFragmentTest : BaseUnitTestNoKoin() {

    val mockViewModel = mock<PokemonHomeViewModel>()

    private fun getHomeFragment() = fragmentWithMockNavController(PokemonHomeFragment())

    @Test
    fun onPokemonResultTest() {
        launchFragment<PokemonHomeFragment>(themeResId = com.ba.pokedex.R.style.Theme_Pokedex) {
            getHomeFragment()
        }.onFragment {

            val spyFragment = spy(it)

            doReturn(mockViewModel).whenever(spyFragment).viewModel
            doNothing().whenever(mockViewModel).getFirst15Pokemons(any())
            doNothing().whenever(spyFragment).loadAllPokemons()

            spyFragment.onPokemonResult(Event.Success(Unit))
            verify(spyFragment, times(1)).onPokemonSuccess()

            spyFragment.onPokemonResult(Event.Failure(Throwable()))
            verify(spyFragment, times(1)).onPokemonFailure(any())
        }
    }

    @Test
    fun onPokemonSuccessTest() {
        launchFragment<PokemonHomeFragment>(themeResId = com.ba.pokedex.R.style.Theme_Pokedex) {
            getHomeFragment()
        }.onFragment {
            val spyFragment = spy(it)


            doReturn(mockViewModel).whenever(spyFragment).viewModel
            doNothing().whenever(mockViewModel).getFirst15Pokemons(any())
            doNothing().whenever(spyFragment).loadAllPokemons()

            spyFragment.onPokemonSuccess()
            verify(spyFragment, times(1)).loadAllPokemons()
        }
    }


    @Test
    fun onPokemonFailureTest() {
        launchFragment<PokemonHomeFragment>(themeResId = com.ba.pokedex.R.style.Theme_Pokedex) {
            getHomeFragment()
        }.onFragment {
            val spyFragment = spy(it)


            doReturn(mockViewModel).whenever(spyFragment).viewModel
            doNothing().whenever(mockViewModel).getFirst15Pokemons(any())
            doNothing().whenever(spyFragment).loadAllPokemons()

            spyFragment.onPokemonFailure(Throwable())
            verify(spyFragment, times(1)).showAlert(text = any())
        }


    }
}