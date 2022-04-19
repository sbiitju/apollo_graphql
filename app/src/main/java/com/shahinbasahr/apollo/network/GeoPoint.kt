package com.shahinbasahr.apollo.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class GeoPoint(
    val lat: Double,
    val lng: Double
) : Parcelable