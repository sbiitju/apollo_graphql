package com.shahinbasahr.apollo.location

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import com.shahinbasahr.apollo.BuildConfig
import com.shahinbasahr.apollo.network.RequestExceptionGraphQl
import com.shahinbashar.apollo.HomeBannersQuery
import com.shahinbashar.apollo.ReverseGeoCodeQuery
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import org.xml.sax.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    private val locationDataSource: LocationNetworkSource,
    @ApplicationContext private val context: Context
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

    override fun getHomeBanners(latLng: LatLng): Observable<HomeBannersQuery.Result> {
        return locationDataSource.getHomeBanners(latLng.latitude,latLng.longitude)
            .onException(context)
            .map{
                return@map it.getClientHomeBanners?.result
            }
    }

}

fun <T> Observable<T>.onException(appContext: Context): Observable<T> {
    return this.onErrorResumeNext { it: Throwable ->
        Observable.create<T> { emitter ->
               emitter.onError(it)
        }

    }
}
