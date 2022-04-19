package com.shahinbasahr.apollo.maps

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.shahinbasahr.apollo.R
import com.shahinbasahr.apollo.databinding.ActivityMapsBinding
import com.shahinbashar.apollo.ReverseGeoCodeQuery
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import javax.inject.Inject
@AndroidEntryPoint
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val REQUEST_CHECK_GPS_SETTINGS = 1
    }

    private var sydney=ObservableField<LatLng>()
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var viewModel: MapsViewModel
    private var requestingLocationUpdates = false




    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(
            this
        )
    }
    private val locationRequest: LocationRequest by lazy {
        LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            smallestDisplacement = 100f
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
    private lateinit var locationCallback: LocationCallback
    private lateinit var queryTextWatcherDisposable: Disposable



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sydney.set(LatLng(0.0,0.0))
        viewModel=ViewModelProvider(this)[MapsViewModel::class.java]
        binding.viewmodel = viewModel
        createLocationRequest()
        getCurrentLocation()
        binding.btnMyLocation.setOnClickListener{
            createLocationRequest()
            getCurrentLocation()
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                p0.let { result ->
                    Log.d("Result", result.locations.toString())
                    sydney.set(LatLng(result.lastLocation.latitude,result.lastLocation.longitude))
                    /*for (location in result.locations) {
                                    showToast("Location Changed - Lat: " + location.latitude + ", Lng: " + location.longitude)
                                }*/
                    //                    Logger.i("Location Changed - Lat: " + result.lastLocation.latitude + ", Lng: " + result.lastLocation.longitude)
                }
            }
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        mMap.uiSettings.isCompassEnabled = false
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = false

        mMap.setOnCameraMoveListener {
            viewModel.isChecking.set(true)
            viewModel.isLocationAvailable.set(false)
            viewModel.buttonSelectLocationText.set("Checking...")
        }

        mMap.setOnCameraIdleListener {
            viewModel.getLocationAddress(mMap.cameraPosition.target)
        }

    }


    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()!! /* Looper */
        )
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun createLocationRequest() {
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val locationSettingsTask: Task<LocationSettingsResponse> =
            client.checkLocationSettings(builder.build())

        locationSettingsTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                requestingLocationUpdates = true
                startLocationUpdates()
            } else {
                task.exception?.let { exception ->
                    if (exception is ResolvableApiException) {
                        try {
                            exception.startResolutionForResult(this, REQUEST_CHECK_GPS_SETTINGS)
                        } catch (sendEx: IntentSender.SendIntentException) {
                            sendEx.printStackTrace()
                            goToGpsSettings()
                        }
                    } else {
                        exception.printStackTrace()
                        goToGpsSettings()
                    }
                }
            }
        }
    }


    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let { location ->
                    Log.d("location", location.toString())
                    sydney.set(LatLng(location.latitude,location.longitude))
                    mMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            sydney.get()!!,
                            16.5f
                        )
                    )
                    viewModel.userLatLng = LatLng(location.latitude, location.longitude)
                }
            } else {
                task.exception?.printStackTrace()
            }
        }
    }

    private fun goToGpsSettings() {
        try {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        } catch (e: ActivityNotFoundException) {
        }
    }

}