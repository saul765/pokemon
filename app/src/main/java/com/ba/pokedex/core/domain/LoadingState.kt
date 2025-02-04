package com.ba.pokedex.core.domain

import androidx.annotation.StringRes
import com.ba.pokedex.core.data.ZERO_INTEGER

data class LoadingState(
    val isLoading: Boolean = false,
    @StringRes val message: Int? = ZERO_INTEGER
)