package com.bharadwaja.cities.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bharadwaja.cities.data.CitiesRepository
import com.bharadwaja.cities.data.CityInformation
import java.util.*
import kotlin.collections.ArrayList

class CitiesViewModel(application: Application) : AndroidViewModel(application) {

    val mContext: Context = application.applicationContext
    val citiesRepository = CitiesRepository()

    lateinit var citiesLiveData: LiveData<LinkedList<CityInformation>>
    fun getAllCities() {
        citiesLiveData = MutableLiveData<LinkedList<CityInformation>>()
        citiesRepository.sortRecordsByCityNameInAlphabeticalOrder(mContext)

        citiesLiveData = citiesRepository.getCitiesLiveData()
    }

    fun getRequiredLiveData(): LiveData<LinkedList<CityInformation>> {
        return citiesLiveData
    }
}