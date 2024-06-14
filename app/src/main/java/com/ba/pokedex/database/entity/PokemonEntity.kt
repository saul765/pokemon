package com.ba.pokedex.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ba.pokedex.domain.uimodel.PokemonItemUIModel

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "pokedex_number")
    val pokedexNumber: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)

fun PokemonEntity.toUIModel(): PokemonItemUIModel = PokemonItemUIModel(
    id = pokedexNumber,
    imageUrl = imageUrl,
    name = name
)