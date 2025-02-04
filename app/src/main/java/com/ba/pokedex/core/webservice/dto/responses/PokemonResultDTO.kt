package com.ba.pokedex.core.webservice.dto.responses

import com.ba.pokedex.core.data.ZERO_INTEGER
import com.ba.pokedex.core.domain.Pokemon
import com.ba.pokedex.core.domain.PokemonResult

data class PokemonResultDTO(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PokemonDTO?>? = emptyList()
)

fun PokemonResultDTO.toDomain(): PokemonResult = PokemonResult(
    count = count ?: ZERO_INTEGER,
    next = next.orEmpty(),
    previous = previous.orEmpty(),
    results = results?.map { it?.toDomain() ?: Pokemon() } ?: emptyList()
)
