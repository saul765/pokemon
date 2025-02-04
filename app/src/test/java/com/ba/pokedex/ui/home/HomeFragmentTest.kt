package com.ba.pokedex.ui.home

import androidx.fragment.app.testing.launchFragment
import com.ba.pokedex.base.BaseUnitTest
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

class HomeFragmentTest : BaseUnitTest() {

    val mockViewModel = mock<HomeViewModel>()

    private fun getHomeFragment() = fragmentWithMockNavController(HomeFragment())

    @Test
    fun onPokemonResultTest() {
        launchFragment<HomeFragment>(themeResId = com.ba.pokedex.R.style.Theme_Pokedex) {
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
        launchFragment<HomeFragment>(themeResId = com.ba.pokedex.R.style.Theme_Pokedex) {
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
        launchFragment<HomeFragment>(themeResId = com.ba.pokedex.R.style.Theme_Pokedex) {
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