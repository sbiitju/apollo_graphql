package com.aisavent.data.location


import com.shahinbashar.apollo.ReverseGeoCodeQuery
import io.reactivex.Observable

interface LocationDataSource {

    fun reverseGeoCode(latitude: Double, longitude: Double): Observable<ReverseGeoCodeQuery.Result>

}