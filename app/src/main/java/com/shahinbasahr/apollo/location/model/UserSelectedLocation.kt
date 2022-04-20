package com.shahinbasahr.apollo.location.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSelectedLocation(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var address: String? = null
) : Parcelable {
    override fun toString(): String {
        return "UserLocation(latitude=$latitude, longitude=$longitude, address=$address)"
    }
}