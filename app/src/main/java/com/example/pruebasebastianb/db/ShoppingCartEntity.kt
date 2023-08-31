package com.example.pruebasebastianb.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_cart")
data class ShoppingCartEntity(
    @PrimaryKey var id: Int = 0
)
