package com.example.pruebasebastianb.view

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebasebastianb.model.ResultsModel
import com.example.pruebasebastianb.model.ResultsProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.initializer
import dagger.hilt.android.qualifiers.ApplicationContext

class ResultsViewModel: ViewModel() {

    var resultsProvider = ResultsProvider()

    var data = MutableLiveData<ResultsModel>()

    suspend fun getData(): ResultsModel {
        val auxData = resultsProvider.getPokemons()
        data.postValue(auxData)
        return auxData
    }

    suspend fun setData(resultsModel: ResultsModel) {
        data.postValue(resultsModel)
    }

}