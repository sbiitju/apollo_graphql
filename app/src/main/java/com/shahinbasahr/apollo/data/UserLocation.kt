package com.shahinbasahr.apollo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserLocation (
    val lat: Double,
    val lang: Double,
    val address: String
) : Parcelable