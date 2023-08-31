package com.example.pruebasebastianb.model

import com.example.pruebasebastianb.helpers.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailsProvider {

    private var retrofit = RetrofitHelper.getRetrofit()

    suspend fun getDetails(id: Int): DetailsModel? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(DetailsApiClient::class.java).getDetails(id)
            var aux = response.body() ?: DetailsModel(
                0,
                "",
                arrayListOf<AbilityModel>(AbilityModel(AbilityItemModel(""))),
                0,
                arrayListOf<MovesModel>(MovesModel(MovesItemModel(""))),
                arrayListOf<TypesModel>(TypesModel(TypesItemModel(""))),
                0)
            if (aux.name == "") {
                null
            } else {
                aux
            }
        }
    }

}