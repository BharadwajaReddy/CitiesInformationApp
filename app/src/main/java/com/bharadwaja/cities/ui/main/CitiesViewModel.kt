package com.bharadwaja.cities.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.bharadwaja.cities.data.CitiesRepository
import com.bharadwaja.cities.data.CityInformation
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

import java.util.*


class CitiesViewModel(application: Application) : AndroidViewModel(application) {

    val mContext: Context = application.applicationContext
    val citiesRepository = CitiesRepository()
    var citiesLiveData: LiveData<LinkedList<CityInformation>>
    var filteredCitiesLiveData: LiveData<LinkedList<CityInformation>>

    init {
        citiesLiveData = MutableLiveData<LinkedList<CityInformation>>()
        filteredCitiesLiveData = MutableLiveData<LinkedList<CityInformation>>()
    }


    fun getAllCities() {

        viewModelScope.launch(Dispatchers.IO) {
            citiesRepository.sortRecordsByCityNameInAlphabeticalOrder(mContext)
        }
        citiesLiveData = citiesRepository.getCitiesLiveData()
    }

    fun getRequiredLiveData(): LiveData<LinkedList<CityInformation>> {
        return citiesLiveData
    }


    fun getFilteredData(query: String/*, filteredlist: LinkedList<CityInformation>*/) {
        viewModelScope.launch(Dispatchers.IO) {
            citiesRepository.filterRecordsByCityName(/*filteredlist,*/ query)
        }
        filteredCitiesLiveData = citiesRepository.getfilteredCitiesLiveData()
    }

    fun getFilteredLiveData(): LiveData<LinkedList<CityInformation>> {

        return filteredCitiesLiveData
    }


}