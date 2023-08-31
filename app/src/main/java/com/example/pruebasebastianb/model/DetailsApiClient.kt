package com.example.pruebasebastianb.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApiClient {

    @GET("pokemon/{id}")
    suspend fun getDetails(@Path("id") id: Int): Response<DetailsModel>

}