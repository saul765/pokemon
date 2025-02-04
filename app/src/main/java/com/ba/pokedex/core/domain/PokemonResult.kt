package com.ba.pokedex.core.domain

import kotlin.collections.List

data class PokemonResult(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Pokemon>
)