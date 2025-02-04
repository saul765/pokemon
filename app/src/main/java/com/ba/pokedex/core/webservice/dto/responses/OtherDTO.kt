package com.ba.pokedex.core.webservice.dto.responses

import com.ba.pokedex.core.domain.OfficialArtwork
import com.ba.pokedex.core.domain.Other
import com.google.gson.annotations.SerializedName

data class OtherDTO(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtworkDTO?
)

fun OtherDTO.toDomain(): Other =
    Other(
        officialArtwork = officialArtwork?.toDomain() ?: OfficialArtwork()
    )