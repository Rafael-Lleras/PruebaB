package com.example.pruebasebastianb.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey var id: Int = 0,
    var name: String,
    var url: String
) {

    private fun getId(): Int? = if (url.split("/").size > 6) url.split("/")[6].toInt() else null

    fun getUrlShinyImage(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${getId()}.png"
    }

}
