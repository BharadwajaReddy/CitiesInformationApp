package com.bharadwaja.cities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bharadwaja.cities.ui.main.CitiesFragment

class CitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cities_information_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CitiesFragment.newInstance())
                .commitNow()
        }
    }
}