package com.ba.pokedex.core.domain

import com.ba.pokedex.core.data.EMPTY_CHARACTER
import com.ba.pokedex.core.data.ZERO_INTEGER
import com.ba.pokedex.core.database.entity.PokemonEntity
import com.ba.pokedex.core.domain.uimodel.PokemonItemUIModel


data class PokemonItem(
    val id: Int = ZERO_INTEGER,
    val sprites: Sprite = Sprite(),
    val name: String = EMPTY_CHARACTER
)


fun PokemonItem.toUIModel(): PokemonItemUIModel = PokemonItemUIModel(
    id = id,
    imageUrl = sprites.other.officialArtwork.frontDefault,
    name = name
)

fun PokemonItem.toEntity(): PokemonEntity = PokemonEntity(
    pokedexNumber = id,
    name = name,
    imageUrl = sprites.other.officialArtwork.frontDefault
)