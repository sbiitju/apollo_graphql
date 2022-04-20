package com.shahinbasahr.apollo.nav_activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import com.google.android.gms.maps.model.LatLng
import com.shahinbasahr.apollo.R
import com.shahinbasahr.apollo.data.UserLocation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavActivity : AppCompatActivity() {

    companion object {
        const val ARGUMENT_USER_LOCATION = "argument_user_location"
    }

    var userLocation: UserLocation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        userLocation = intent.extras?.get(ARGUMENT_USER_LOCATION) as? UserLocation

    }
}