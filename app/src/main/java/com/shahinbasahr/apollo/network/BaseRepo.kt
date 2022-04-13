package com.shahinbasahr.apollo.network

import com.shahinbashar.apollo.GetZoneQuery
import io.reactivex.Observable

interface BaseRepo {
    fun getZone(latitude: Double, longitude: Double): Observable<GetZoneQuery.Data>
}