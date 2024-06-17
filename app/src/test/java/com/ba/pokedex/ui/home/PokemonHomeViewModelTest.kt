package com.ba.pokedex.ui.home

import androidx.paging.PagingData
import com.ba.pokedex.base.BaseUnitTest
import com.ba.pokedex.base.BaseUnitTestNoKoin
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel
import com.ba.pokedex.ui.PokemonHomeViewModel
import com.ba.pokedex.usecases.IGetHomePokemonUseCase
import com.ba.pokedex.usecases.IGetPokemonUseCase
import com.ba.pokedex.utils.livedata.Event
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.test.mock.declareMock
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.HttpException

class PokemonHomeViewModelTest : BaseUnitTestNoKoin() {


    private val pokemonHomeUseCase by lazy { declareMock<IGetHomePokemonUseCase>() }

    private val pokemonPagedUseCase by lazy { declareMock<IGetPokemonUseCase>() }


    private val viewModel by lazy {

        PokemonHomeViewModel(
            pokemonHomeUseCase,
            pokemonPagedUseCase
        )
    }

    @Before
    fun setup() {
        val expectedFlow =
            flowOf(PagingData.from(listOf(PokemonItemUIModel(1, "Test Pokemon", "Test Url"))))
        whenever(pokemonPagedUseCase.execute()).thenReturn(expectedFlow)
    }

    @Test
    fun getFirst15PokemonsSuccessTest() {
        runBlocking {
            doReturn(Unit).whenever(pokemonHomeUseCase).execute(any())

            viewModel.getFirst15Pokemons(context)
            println(viewModel.getPokemonEvent.value)
            val eventSuccess = viewModel.getPokemonEvent.value as Event.Success
            assertEquals(Unit, eventSuccess.data)
            verify(pokemonHomeUseCase, times(1)).execute(any())
        }
    }

    @Test
    fun getFirst15PokemonsFailureTest() {
        runBlocking {
            doThrow(HttpException(mock())).whenever(pokemonHomeUseCase).execute(any())

            viewModel.getFirst15Pokemons(context)
            val eventFailure = viewModel.getPokemonEvent.value as Event.Failure
            assertNotNull(eventFailure.throwable)
            verify(pokemonHomeUseCase, times(1)).execute(any())
        }
    }

}