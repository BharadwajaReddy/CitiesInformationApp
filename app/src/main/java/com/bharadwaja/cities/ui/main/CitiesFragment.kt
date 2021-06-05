package com.bharadwaja.cities.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bharadwaja.cities.R

class CitiesFragment : Fragment() {

    companion object {
        fun newInstance() = CitiesFragment()
    }

    private lateinit var viewModel: CitiesInformationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.cities_information_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CitiesInformationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}