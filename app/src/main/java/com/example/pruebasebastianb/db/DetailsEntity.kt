package com.example.pruebasebastianb.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class DetailsEntity(
    @PrimaryKey var id: Int = 0,
    var name: String,
    var height: Int,
    var weight: Int,
    var abilities: String,
    var types: String,
    var moves: String
) {

    fun getUrlHomeImage(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$id.png"
    }

}
