package com.ba.pokedex.webservice.dto.responses

import com.ba.pokedex.domain.Other
import com.ba.pokedex.domain.Sprite

data class SpriteDTO(val other: OtherDTO?)

fun SpriteDTO.toDomain(): Sprite = Sprite(
    other = other?.toDomain() ?: Other()
)
