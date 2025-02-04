package com.ba.pokedex.core.domain

import com.ba.pokedex.core.data.EMPTY_CHARACTER

data class Pokemon(
    val name: String = EMPTY_CHARACTER,
    val url: String = EMPTY_CHARACTER
)