package com.ba.pokedex.webservice.dto.responses

import com.ba.pokedex.domain.OfficialArtwork
import com.google.gson.annotations.SerializedName

data class OfficialArtworkDTO(@SerializedName("front_default") val frontDefault: String?)

fun OfficialArtworkDTO.toDomain(): OfficialArtwork =
    OfficialArtwork(
        frontDefault = frontDefault.orEmpty()
    )
