package com.ba.pokedex.webservice.dto.responses

import com.ba.pokedex.domain.Pokemon

data class PokemonDTO(
    val name: String?,
    val url: String?
)

fun PokemonDTO.toDomain(): Pokemon {
    return Pokemon(
        name = name.orEmpty(),
        url = url.orEmpty()
    )
}