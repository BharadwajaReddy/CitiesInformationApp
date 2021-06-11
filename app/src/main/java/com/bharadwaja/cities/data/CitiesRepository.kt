package com.bharadwaja.cities.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bharadwaja.cities.utils.BinarySearchAlgorithm
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.util.*

class CitiesRepository {


    val citiesListLivedata = MutableLiveData<LinkedList<CityInformation>>()
    val citiesList = LinkedList<CityInformation>()
    fun getAllCityRecordsFromJsonFromAssets(mContext: Context): String? {

        var json: String? = null
        try {
            val inputStream: InputStream = mContext.assets.open("cities.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun sortRecordsByCityNameInAlphabeticalOrder(mContext: Context) {


        val citiesJSONArray = JSONArray(getAllCityRecordsFromJsonFromAssets(mContext))
        citiesList.clear()
        for (i in 0 until citiesJSONArray.length()) {
            val cityInfoJsonObj: JSONObject = citiesJSONArray.getJSONObject(i)
            val cityName: String = cityInfoJsonObj.getString("name").trim()
            val country: String = cityInfoJsonObj.getString("country")
            val id: Int = cityInfoJsonObj.getInt("_id")
            val coordinatesJsonObj = cityInfoJsonObj.getJSONObject("coord")
            val latitude = coordinatesJsonObj.getDouble("lat")
            val longitude = coordinatesJsonObj.getDouble("lon")
            citiesList.add(CityInformation(country, cityName, id, Coordinates(longitude, latitude)))
        }

        Collections.sort(citiesList, CityNameComparator())
        citiesListLivedata.postValue(citiesList)


    }


    class CityNameComparator : Comparator<CityInformation> {
        override fun compare(o1: CityInformation?, o2: CityInformation?): Int {
            if (o1 == null || o2 == null) {
                return 0;
            }
            return o1.CityName.compareTo(o2.CityName)
        }
    }


    fun getCitiesLiveData(): LiveData<LinkedList<CityInformation>> {
        return citiesListLivedata
    }


}