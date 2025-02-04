package com.ba.pokedex.core.webservice.dto.responses

import com.ba.pokedex.core.data.ZERO_INTEGER
import com.ba.pokedex.core.domain.PokemonItem
import com.ba.pokedex.core.domain.Sprite

data class PokemonItemDTO(
    val id: Int?,
    val sprites: SpriteDTO?,
    val name: String?
)

fun PokemonItemDTO.toDomain(): PokemonItem =
    PokemonItem(
        id = id ?: ZERO_INTEGER,
        sprites = sprites?.toDomain() ?: Sprite(),
        name = name.orEmpty()
    )
