package com.ba.pokedex.usecases

import com.ba.pokedex.base.BaseUnitTestNoKoin
import com.ba.pokedex.repositories.pokemon.IPokemonDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import org.koin.test.mock.declareMock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetPokemonUseCaseTest : BaseUnitTestNoKoin() {

    private val pokemonRepository by lazy { declareMock<IPokemonDataSource.Repository>() }

    private val pokemonUseCase by inject<IGetPokemonUseCase>()


    @Test
    fun executionGetPokemonUseCaseTest() {
        runBlocking {

            doReturn(FakePagingSource()).whenever(pokemonRepository).getPokemonsPaged()

            pokemonUseCase.execute().first()

            verify(pokemonRepository, times(1)).getPokemonsPaged()
        }
    }
}