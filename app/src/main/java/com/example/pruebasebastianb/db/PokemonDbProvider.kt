package com.example.pruebasebastianb.db

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.pruebasebastianb.helpers.RoomHelper

class PokemonDbProvider(context: AppCompatActivity) {

    private var dataBase = Room.databaseBuilder(context, RoomHelper::class.java, "PokemonsDataBase").build()

    fun getDatBase() = dataBase

}