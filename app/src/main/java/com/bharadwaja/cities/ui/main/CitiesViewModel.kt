package com.bharadwaja.cities.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.bharadwaja.cities.data.CitiesRepository
import com.bharadwaja.cities.data.CityInformation
import com.bharadwaja.cities.utils.BinarySearchAlgorithm
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

import java.util.*


class CitiesViewModel(application: Application) : AndroidViewModel(application) {

    val mContext: Context = application.applicationContext
    val citiesRepository = CitiesRepository()
    var citiesLiveData: LiveData<LinkedList<CityInformation>>
    var filteredCitiesLiveData: MutableLiveData<LinkedList<CityInformation>>

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


    fun getFilteredData(query: String, citiesList: LinkedList<CityInformation>) {
        viewModelScope.launch(Dispatchers.IO) {
            //  citiesRepository.filterRecordsByCityName(/*filteredlist,*/ query)
            val filteredData = BinarySearchAlgorithm().cityBinarySearch(citiesList, query)
            filteredCitiesLiveData.postValue(filteredData)
        }

    }

    fun getFilteredLiveData(): LiveData<LinkedList<CityInformation>> {

        return filteredCitiesLiveData
    }


}