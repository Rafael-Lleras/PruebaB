package com.example.pruebasebastianb.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebasebastianb.model.DetailsModel
import com.example.pruebasebastianb.model.DetailsProvider
import com.example.pruebasebastianb.model.ResultsModel

class DetailsViewModel: ViewModel() {

    var detailsProvider = DetailsProvider()

    var data = MutableLiveData<DetailsModel>()

    suspend fun getData(id: Int): DetailsModel? {
        val auxData = detailsProvider.getDetails(id)
        return if (auxData == null) {
            null
        } else {
            data.postValue(auxData!!)
            auxData
        }
    }

    suspend fun setData(detailsModel: DetailsModel) {
        data.postValue(detailsModel)
    }

}