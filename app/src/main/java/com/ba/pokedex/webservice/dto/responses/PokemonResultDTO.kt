package com.ba.pokedex.webservice.dto.responses

import com.ba.pokedex.data.ZERO
import com.ba.pokedex.domain.Pokemon
import com.ba.pokedex.domain.PokemonResult

data class PokemonResultDTO(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: kotlin.collections.List<PokemonDTO?>? = emptyList()
)

fun PokemonResultDTO.toDomain(): PokemonResult = PokemonResult(
    count = count ?: ZERO,
    next = next.orEmpty(),
    previous = previous.orEmpty(),
    results = results?.map { it?.toDomain() ?: Pokemon() } ?: emptyList()
)
