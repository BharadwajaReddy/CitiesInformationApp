package com.bharadwaja.cities.ui.main

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.SearchView

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

    var citiesAdaptor: CitiesAdaptor? = null
    private lateinit var citiesViewModel: CitiesViewModel
    private lateinit var observer: Observer<LinkedList<CityInformation>>
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
        val cities: LinkedList<CityInformation> = LinkedList()
        citiesAdaptor = activity?.let { CitiesAdaptor(it, cities) }
        cities_recyclerview.adapter = citiesAdaptor
        observer = Observer<LinkedList<CityInformation>> {
            cities.addAll(it)
            citiesAdaptor?.notifyDataSetChanged()
        }
        setHasOptionsMenu(true);
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        citiesViewModel = ViewModelProvider(this).get(CitiesViewModel::class.java)
        citiesViewModel.getAllCities()
        citiesViewModel.getRequiredLiveData().observe(viewLifecycleOwner, observer)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val item = menu.findItem(R.id.action_search);
        val searchView = item?.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                citiesAdaptor?.getFilter()?.filter(newText)
                return false
            }
        })

    }

}