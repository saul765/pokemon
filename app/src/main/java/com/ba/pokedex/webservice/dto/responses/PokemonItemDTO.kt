package com.ba.pokedex.webservice.dto.responses

import com.ba.pokedex.data.ZERO
import com.ba.pokedex.domain.PokemonItem
import com.ba.pokedex.domain.Sprite

data class PokemonItemDTO(
    val id: Int?,
    val sprites: SpriteDTO?,
    val name: String?
)

fun PokemonItemDTO.toDomain(): PokemonItem =
    PokemonItem(
        id = id ?: ZERO,
        sprites = sprites?.toDomain() ?: Sprite(),
        name = name.orEmpty()
    )
