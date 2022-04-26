package com.shahinbasahr.apollo.getitem.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoPoint(
    val lat: Double,
    val lng: Double
) : Parcelable
