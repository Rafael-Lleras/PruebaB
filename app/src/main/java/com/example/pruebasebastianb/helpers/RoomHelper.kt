package com.example.pruebasebastianb.helpers

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebasebastianb.db.DetailsDao
import com.example.pruebasebastianb.db.DetailsEntity
import com.example.pruebasebastianb.db.PokemonDao
import com.example.pruebasebastianb.db.PokemonEntity
import com.example.pruebasebastianb.db.ShoppingCartEntity
import com.example.pruebasebastianb.db.SoppingCartDao

@Database(entities = [PokemonEntity::class, DetailsEntity::class, ShoppingCartEntity::class], version = 1)
abstract class RoomHelper: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    abstract fun detailsDao(): DetailsDao

    abstract fun shoppingCartDao(): SoppingCartDao

}