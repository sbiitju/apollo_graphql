package com.shahinbasahr.apollo.maps

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.aisavent.data.location.LocationDataSource
import com.google.android.gms.maps.model.LatLng
import com.shahinbasahr.apollo.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    @ApplicationContext private val application: Context,
    private val locationRepository: LocationDataSource

) : ViewModel() {
    var districtCityArea: Triple<String, String, String>? = null
    var etSearch = ObservableField<String>()
    var compositeDisposable = CompositeDisposable()
    val isChecking by lazy { ObservableBoolean(false) }
    val isLocationAvailable by lazy { ObservableBoolean(false) }
    val buttonSelectLocationText by lazy { ObservableField<String>(application.getString(R.string.checking)) }
    lateinit var userLatLng: LatLng
    fun getLocationAddress(latLng: LatLng) {
        compositeDisposable.add(
            locationRepository.reverseGeoCode(latLng.latitude, latLng.longitude)
                .withScheduler()
                .subscribe({
                    etSearch.set("${it.address}, ${it.area}")
                    isChecking.set(false)
                    isLocationAvailable.set(true)
                    buttonSelectLocationText.set("Select Location")
                    districtCityArea = Triple(
                        it.district ?: "",
                        it.city ?: "",
                        it.area ?: ""
                    )
                    Log.d("Update", districtCityArea.toString())

                }, { throwable ->
                    throwable.printStackTrace()
                    isChecking.set(false)
                    Log.d("Update", throwable.toString())
                    buttonSelectLocationText.set("Service not available")
                })
        )


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

fun <T> Observable<T>.withScheduler(): Observable<T> {
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}