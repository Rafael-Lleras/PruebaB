package com.example.pruebasebastianb.model

import com.google.gson.annotations.SerializedName

data class DetailsModel(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("abilities") var abilities: ArrayList<AbilityModel>,
    @SerializedName("height") var height: Int,
    @SerializedName("moves") var moves: ArrayList<MovesModel>,
    @SerializedName("types") var types: ArrayList<TypesModel>,
    @SerializedName("weight") var weight: Int
) {

    fun getUrlHomeImage(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"
    }

}
