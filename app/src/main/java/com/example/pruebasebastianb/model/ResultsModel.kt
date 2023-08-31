package com.example.pruebasebastianb.model

import com.google.gson.annotations.SerializedName

data class ResultsModel(@SerializedName("results") var results: ArrayList<PokemonModel>) {
}