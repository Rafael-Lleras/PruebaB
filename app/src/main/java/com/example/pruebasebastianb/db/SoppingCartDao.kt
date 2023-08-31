package com.example.pruebasebastianb.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SoppingCartDao {

    @Query("SELECT pokemon.* FROM shopping_cart, pokemon WHERE shopping_cart.id = pokemon.id")
    suspend fun getAllItems(): List<PokemonEntity>?

    @Insert
    suspend fun insert(item: ShoppingCartEntity)

    @Query("DELETE FROM shopping_cart WHERE id = :itemId")
    suspend fun deleteItemById(itemId: Int)

    @Query("DELETE FROM shopping_cart")
    suspend fun deleteItems()

}