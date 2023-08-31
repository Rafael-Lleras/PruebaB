package com.example.pruebasebastianb.model

import com.google.gson.annotations.SerializedName

data class MovesModel(
    @SerializedName("move") var move: MovesItemModel
)
