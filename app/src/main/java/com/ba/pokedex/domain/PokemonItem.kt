package com.ba.pokedex.domain

import com.ba.pokedex.data.EMPTY_CHARACTER
import com.ba.pokedex.data.ZERO
import com.ba.pokedex.database.entity.PokemonEntity
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel

data class PokemonItem(
    val id: Int = ZERO,
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