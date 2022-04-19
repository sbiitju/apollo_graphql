package com.aisavent.data.location

import android.app.Application
import com.shahinbashar.apollo.ReverseGeoCodeQuery
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    private val locationDataSource: LocationNetworkSource,
) : LocationDataSource {

    override fun reverseGeoCode(
        latitude: Double,
        longitude: Double
    ): Observable<ReverseGeoCodeQuery.Result> {
        return locationDataSource.reverseGeoCode(latitude, longitude)
            .map { data ->
                return@map data.reverseGeoCode?.result
            }
    }

}


