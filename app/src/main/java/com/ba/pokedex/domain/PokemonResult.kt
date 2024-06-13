package com.ba.pokedex.domain

data class PokemonResult(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Pokemon>
)