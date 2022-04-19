package com.aisavent.data.location

import com.shahinbashar.apollo.ReverseGeoCodeQuery
import io.reactivex.Observable


interface LocationNetworkSource {

    fun reverseGeoCode(latitude: Double, longitude: Double): Observable<ReverseGeoCodeQuery.Data>


}