package com.shahinbasahr.apollo.getitem.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    val id: String,
    val name: String? = null
):Parcelable
