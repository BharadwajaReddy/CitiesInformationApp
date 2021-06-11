package com.bharadwaja.cities


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bharadwaja.cities.data.CityInformation
import com.bharadwaja.cities.data.Coordinates

import java.util.*

class CitiesAdaptor(val context: Context, val citiesList: LinkedList<CityInformation>) :
    RecyclerView.Adapter<CitiesAdaptor.CitiesViewHolder>()/*, Filterable */{


    var cityFilterList = LinkedList<CityInformation>()

    init {
        cityFilterList = citiesList
    }

    class CitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val city = view.findViewById<TextView>(R.id.city)
        val country_code = view.findViewById<TextView>(R.id.country_code)
        val longitude = view.findViewById<TextView>(R.id.longitude)
        val latitude = view.findViewById<TextView>(R.id.latitude)
        val city_details_cardview = view.findViewById<CardView>(R.id.city_details_cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view: View = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.city_item_layout,
            parent,
            false
        )
        return CitiesViewHolder(view)

    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        val cityInfo: CityInformation = cityFilterList[position]
        val coordinates: Coordinates = cityInfo.coordinates
        holder.city.text = cityInfo.CityName
        holder.country_code.text = cityInfo.Country
        val longitude = StringBuilder(context.resources.getString(R.string.longitude))
        val latitude = StringBuilder(context.resources.getString(R.string.latitude))
        holder.longitude.text = longitude.append(coordinates.Longitude.toString())
        holder.latitude.text = latitude.append(coordinates.Latitude.toString())

        val locationUri: String =
            "geo:" + coordinates.Latitude.toString() + "," + coordinates.Longitude.toString() + "?z=zoom"
        holder.city_details_cardview.setOnClickListener(View.OnClickListener {
            val gmmIntentUri = Uri.parse(locationUri)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(context.packageManager)?.let {
                context.startActivity(mapIntent)
            }
        })
    }

    override fun getItemCount(): Int {
        return cityFilterList.size
    }

  /*  override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    cityFilterList = citiesList
                } else {
                    *//* var resultList = LinkedList<CityInformation>()

                      for (row in citiesList) {
                          if (row.CityName.lowercase().contains(
                                  constraint.toString().lowercase()
                              ) && row.CityName.lowercase()
                                  .indexOf(constraint.toString().lowercase()) == 0
                          ) {
                              resultList.add(row)
                          }
                      }
                     cityFilterList=resultList*//*

                    cityFilterList =
                        BinarySearchAlgorithm().cityBinarySearch(citiesList, charSearch)

                    // cityFilterList=BinarySearchAlgorithm().countOccurrences(citiesList, citiesList.size, charSearch)

                }
                val filterResults = FilterResults()
                filterResults.values = cityFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                cityFilterList = results?.values as LinkedList<CityInformation>
                notifyDataSetChanged()
            }
        }
    }*/


}

