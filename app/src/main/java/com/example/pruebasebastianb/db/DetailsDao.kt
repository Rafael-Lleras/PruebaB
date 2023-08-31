package com.example.pruebasebastianb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DetailsDao {

    @Query("SELECT * FROM details")
    suspend fun getAllDetails(): List<DetailsEntity>?

    @Query("SELECT * FROM details WHERE id = :detailsId")
    suspend fun getDetailsById(detailsId: Int): DetailsEntity?

    @Update
    suspend fun updateDetails(details: DetailsEntity)

    @Insert
    suspend fun insert(details: DetailsEntity)

    @Query("DELETE FROM details WHERE id = :detailsId")
    suspend fun deleteDetailsById(detailsId: Int)

    @Query("DELETE FROM details")
    suspend fun deleteDetails()

}