package com.shahinbasahr.apollo.location


import com.google.android.gms.maps.model.LatLng
import com.shahinbashar.apollo.HomeBannersQuery
import com.shahinbashar.apollo.ReverseGeoCodeQuery
import io.reactivex.Observable

interface LocationDataSource {

    fun reverseGeoCode(latitude: Double, longitude: Double): Observable<ReverseGeoCodeQuery.Result>
    fun getHomeBanners(latLng: LatLng): Observable<HomeBannersQuery.Result>


}