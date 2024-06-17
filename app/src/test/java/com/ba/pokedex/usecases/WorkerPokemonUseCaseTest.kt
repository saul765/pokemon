package com.ba.pokedex.usecases

import com.ba.pokedex.base.BaseUnitTest
import com.ba.pokedex.data.EMPTY_CHARACTER
import com.ba.pokedex.domain.Pokemon
import com.ba.pokedex.domain.PokemonItem
import com.ba.pokedex.domain.PokemonResult
import com.ba.pokedex.domain.Sprite
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.core.component.inject
import org.koin.test.mock.declareMock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class WorkerPokemonUseCaseTest : BaseUnitTest() {

    private val pokemonRepository by lazy { declareMock<IPokemonDataSource.Repository>() }

    private val pokemonWorkerUseCase by inject<IWorkerPokemonUseCase>()


    @Test
    fun executionWorkerPokemonUseCaseTest() {
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

            doReturn(pokemonResult).whenever(pokemonRepository).getPokemonsAsync(any(), any())
            doReturn(pokemonItem).whenever(pokemonRepository).getPokemonDetailAsync(any())
            doReturn(4).whenever(pokemonRepository).getTotalNumberOfPokemonsLocal()

            val result = pokemonWorkerUseCase.execute(any(), any())

            verify(pokemonRepository, times(1)).getPokemonsAsync(any(), any())
            verify(pokemonRepository, times(1)).getPokemonDetailAsync(any())
            verify(pokemonRepository, times(1)).savePokemonsLocal(any())
            verify(pokemonRepository, times(1)).getTotalNumberOfPokemonsLocal()

            Assert.assertEquals(4, result)
        }
    }

}