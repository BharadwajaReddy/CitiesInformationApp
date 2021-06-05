package com.bharadwaja.backbaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bharadwaja.backbaseapp.ui.main.CitiesFragment

class CitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CitiesFragment.newInstance())
                .commitNow()
        }
    }
}