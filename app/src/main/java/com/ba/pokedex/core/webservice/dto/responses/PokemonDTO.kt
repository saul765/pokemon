package com.ba.pokedex.core.webservice.dto.responses

import com.ba.pokedex.core.domain.Pokemon

data class PokemonDTO(
    val name: String?,
    val url: String?
)

fun PokemonDTO.toDomain(): Pokemon = Pokemon(
    name = name.orEmpty(),
    url = url.orEmpty()
)
