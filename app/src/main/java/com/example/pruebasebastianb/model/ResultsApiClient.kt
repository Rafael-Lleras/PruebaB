package com.example.pruebasebastianb.model

import retrofit2.Response
import retrofit2.http.GET

interface ResultsApiClient {

    @GET("pokemon/?limit=50&offset=0")
    suspend fun getAllPokemon(): Response<ResultsModel>

}