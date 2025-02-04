package com.ba.pokedex.core.data.exception

import com.ba.pokedex.core.domain.DataError

class HttpException(
    val errorType: DataError.Network,
    override val message: String?
) : Exception(message)