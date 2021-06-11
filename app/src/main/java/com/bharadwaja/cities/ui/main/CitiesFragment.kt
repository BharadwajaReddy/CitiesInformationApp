package com.bharadwaja.cities.ui.main

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bharadwaja.cities.CitiesAdaptor
import com.bharadwaja.cities.R
import com.bharadwaja.cities.data.CityInformation
import java.util.*


class CitiesFragment : Fragment() {
    companion object {
        fun newInstance() = CitiesFragment()
    }

    var cities: LinkedList<CityInformation> = LinkedList()
    var citiesAdaptor: CitiesAdaptor? = null
    private lateinit var citiesViewModel: CitiesViewModel
    private lateinit var observer: Observer<LinkedList<CityInformation>>
    var citiesAdaptorList = LinkedList<CityInformation>()
    private lateinit var filterObserver: Observer<LinkedList<CityInformation>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.cities_information_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cities_recyclerview = view.findViewById<RecyclerView>(R.id.cities_recyclerview)
        cities_recyclerview.layoutManager = LinearLayoutManager(activity)

        citiesAdaptor = activity?.let { CitiesAdaptor(it, citiesAdaptorList) }
        cities_recyclerview.adapter = citiesAdaptor

        setHasOptionsMenu(true)

        citiesViewModel = ViewModelProvider(this).get(CitiesViewModel::class.java)
        citiesViewModel.getAllCities()

        observer = Observer<LinkedList<CityInformation>> {
            cities.addAll(it)
            citiesAdaptorList.addAll(it)
            citiesAdaptor?.notifyDataSetChanged()
        }
        citiesViewModel.getRequiredLiveData().observe(viewLifecycleOwner, observer)
        filterObserver = Observer<LinkedList<CityInformation>> {
            println("===print_filtered_data==" + it)
            if (it.size > 0) {
                citiesAdaptorList.clear()
                citiesAdaptorList.addAll(it)
                citiesAdaptor?.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "No cities found", Toast.LENGTH_LONG).show()
                citiesAdaptorList.clear()
                citiesAdaptor?.notifyDataSetChanged()
            }
        }
        citiesViewModel.getFilteredLiveData().observe(viewLifecycleOwner, filterObserver)

    }


   /* override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }*/


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val item = menu.findItem(R.id.action_search)

        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //citiesAdaptor?.getFilter()?.filter(newText)
                if (newText.length > 0) {
                    citiesViewModel.getFilteredData(newText/*, filteredCities*/)
                } else {
                    citiesAdaptorList = cities
                    citiesAdaptor?.notifyDataSetChanged()
                }
                return false
            }
        })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //for saving query string to handle screen orientation
        //outState.putString("Query", searchView.query.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        //handle for screen orientation
        val savedQuery: String
        if (savedInstanceState != null) {
            savedQuery = savedInstanceState.getString("Query").toString()
            //  searchView.setQuery(savedQuery, true)


        }
    }
}