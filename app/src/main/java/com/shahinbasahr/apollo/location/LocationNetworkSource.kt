package com.shahinbasahr.apollo.location

import com.shahinbashar.apollo.HomeBannersQuery
import com.shahinbashar.apollo.ReverseGeoCodeQuery
import io.reactivex.Observable


interface LocationNetworkSource {

    fun reverseGeoCode(latitude: Double, longitude: Double): Observable<ReverseGeoCodeQuery.Data>

    fun getHomeBanners(lat: Double, lng: Double): Observable<HomeBannersQuery.Data>
}