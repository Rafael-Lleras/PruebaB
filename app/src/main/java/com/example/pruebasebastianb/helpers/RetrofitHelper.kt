package com.example.pruebasebastianb.helpers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val urlApi = "https://pokeapi.co/api/v2/"

    fun getRetrofit() = Retrofit.Builder().baseUrl(urlApi).addConverterFactory(GsonConverterFactory.create()).build()

}