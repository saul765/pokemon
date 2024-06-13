package com.ba.pokedex.domain

import com.ba.pokedex.data.EMPTY_CHARACTER

data class Pokemon(
    val name: String = EMPTY_CHARACTER,
    val url: String = EMPTY_CHARACTER
)