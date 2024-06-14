package com.ba.pokedex.utils

import java.util.Locale

object FormatUtils {

    fun formatPokemonNumber(number: Int): String {
        return String.format(Locale.getDefault(), "#%03d", number)

    }
}