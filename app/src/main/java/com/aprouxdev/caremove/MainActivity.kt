package com.aprouxdev.caremove

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.example.placesdemo.R
import com.aprouxdev.caremove.programmatic_autocomplete.ProgrammaticAutocompleteGeocodingActivity
import com.google.android.libraries.places.api.Places

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiKey = BuildConfig.PLACES_API_KEY
        if (apiKey.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_api_key), Toast.LENGTH_LONG).show()
            return
        }

        // Setup Places Client
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }

        setLaunchActivityClickListener(R.id.autocomplete_button, PlaceAutocompleteActivity::class.java)
        setLaunchActivityClickListener(R.id.autocomplete_address_button, AutocompleteAddressActivity::class.java)
        setLaunchActivityClickListener(R.id.programmatic_autocomplete_button, ProgrammaticAutocompleteGeocodingActivity::class.java)
        setLaunchActivityClickListener(R.id.current_place_button, CurrentPlaceActivity::class.java)
        setLaunchActivityClickListener(R.id.place_and_photo_button, PlaceDetailsAndPhotosActivity::class.java)
        setLaunchActivityClickListener(R.id.is_open_button, PlaceIsOpenActivity::class.java)
    }

    private fun setLaunchActivityClickListener(@IdRes onClickResId: Int, activityClassToLaunch: Class<out AppCompatActivity>) {
        findViewById<Button>(onClickResId).setOnClickListener {
            val intent = Intent(this@MainActivity, activityClassToLaunch)
            startActivity(intent)
        }
    }

}