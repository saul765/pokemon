package com.ba.pokedex.core.webservice.dto.responses

import com.ba.pokedex.core.domain.Other
import com.ba.pokedex.core.domain.Sprite

data class SpriteDTO(val other: OtherDTO?)

fun SpriteDTO.toDomain(): Sprite = Sprite(
    other = other?.toDomain() ?: Other()
)
