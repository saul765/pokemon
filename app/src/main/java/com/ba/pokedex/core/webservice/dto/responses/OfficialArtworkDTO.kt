package com.ba.pokedex.core.webservice.dto.responses

import com.ba.pokedex.core.domain.OfficialArtwork
import com.google.gson.annotations.SerializedName

data class OfficialArtworkDTO(@SerializedName("front_default") val frontDefault: String?)

fun OfficialArtworkDTO.toDomain(): OfficialArtwork =
    OfficialArtwork(
        frontDefault = frontDefault.orEmpty()
    )
