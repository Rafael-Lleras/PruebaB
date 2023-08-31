package com.example.pruebasebastianb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    suspend fun getAllPokemons(): List<PokemonEntity>?

    @Query("SELECT * FROM pokemon WHERE id = :pokemonId")
    suspend fun getPokemonById(pokemonId: Int): PokemonEntity?

    @Update
    suspend fun updatePokemon(pokemon: PokemonEntity)

    @Insert
    suspend fun insert(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemon WHERE id = :pokemonId")
    suspend fun deleteUserById(pokemonId: Int)

    @Query("DELETE FROM pokemon")
    suspend fun deletePokemons()

}