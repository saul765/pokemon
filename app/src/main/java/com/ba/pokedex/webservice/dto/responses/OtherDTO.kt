package com.ba.pokedex.webservice.dto.responses

import com.ba.pokedex.domain.OfficialArtwork
import com.ba.pokedex.domain.Other
import com.google.gson.annotations.SerializedName

data class OtherDTO(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtworkDTO?
)

fun OtherDTO.toDomain(): Other =
    Other(
        officialArtwork = officialArtwork?.toDomain() ?: OfficialArtwork()
    )