package com.ba.pokedex.usecases

import androidx.work.testing.WorkManagerTestInitHelper
import com.ba.pokedex.base.BaseUnitTest
import com.ba.pokedex.data.EMPTY_CHARACTER
import com.ba.pokedex.database.entity.PokemonEntity
import com.ba.pokedex.domain.Pokemon
import com.ba.pokedex.domain.PokemonItem
import com.ba.pokedex.domain.PokemonResult
import com.ba.pokedex.domain.Sprite
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.core.component.inject
import org.koin.test.mock.declareMock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetHomePokemonUseCaseTest : BaseUnitTest() {

    private val pokemonRepository by lazy { declareMock<IPokemonDataSource.Repository>() }
    private val pokemonHomeUseCaseMock by lazy { declareMock<GetHomePokemonUseCase>() }

    private val pokemonHomeUseCase by inject<IGetHomePokemonUseCase>()

    @Before
    fun setup() {
        WorkManagerTestInitHelper.initializeTestWorkManager(context)
    }

    @Test
    fun downloadAndSaveHomePokemonUseCaseSuccessTest() {
        val pokemonResult = PokemonResult(
            0,
            EMPTY_CHARACTER,
            EMPTY_CHARACTER,
            listOf(
                Pokemon("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")
            )
        )

        val pokemonItem =
            PokemonItem(1, Sprite(), "bulbasaur")

        runBlocking {
            doReturn(emptyList<PokemonEntity>()).whenever(pokemonRepository).getPokemonsLocal()
            doReturn(pokemonResult).whenever(pokemonRepository).getPokemonsAsync(any(), any())
            doReturn(pokemonItem).whenever(pokemonRepository).getPokemonDetailAsync(any())

            pokemonHomeUseCase.execute(context)
            verify(pokemonRepository, times(1)).getPokemonsLocal()
            verify(pokemonRepository, times(2)).getPokemonsAsync(any(), any())
            verify(pokemonRepository, times(2)).getPokemonDetailAsync(any())
            verify(pokemonRepository, times(2)).savePokemonsLocal(any())
        }
    }

    @Test
    fun downloadAndSaveHomePokemonUseCaseFailureTest() {
        val pokemonList = listOf(
            PokemonEntity(1, 1, "bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")
        )

        runBlocking {
            doReturn(pokemonList).whenever(pokemonRepository).getPokemonsLocal()

            pokemonHomeUseCase.execute(context)
            verify(pokemonRepository, times(1)).getPokemonsLocal()
            verify(pokemonRepository, times(0)).getPokemonsAsync(any(), any())
            verify(pokemonRepository, times(0)).getPokemonDetailAsync(any())
            verify(pokemonRepository, times(0)).savePokemonsLocal(any())
            verify(pokemonHomeUseCaseMock, times(0)).pokedexLoadBatch(context)
        }

    }

}