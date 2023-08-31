package com.example.pruebasebastianb.model

import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String
    ) {

    fun getId(): Int? = if (url.split("/").size > 6) url.split("/")[6].toInt() else null

    fun getUrlShinyImage(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${getId()}.png"
    }

    override fun toString(): String {
        return "[id = ${getId()}, name = $name, url = $url]"
    }

}
