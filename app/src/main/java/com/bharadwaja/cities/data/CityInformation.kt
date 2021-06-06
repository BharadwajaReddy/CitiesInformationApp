package com.bharadwaja.cities.data

data class CityInformation(
    val Country: String,
    val CityName: String,
    val Id: Int,
    val coordinates: Coordinates
){

/*    override fun compareTo(other: Date) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> day - other.day
    }*/

}