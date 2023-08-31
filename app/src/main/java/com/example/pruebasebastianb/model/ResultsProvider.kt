package com.example.pruebasebastianb.model

import com.example.pruebasebastianb.helpers.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResultsProvider {

    private var retrofit = RetrofitHelper.getRetrofit()

    suspend fun getPokemons(): ResultsModel {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ResultsApiClient::class.java).getAllPokemon()
            response.body() ?: ResultsModel(arrayListOf())
        }
    }

}