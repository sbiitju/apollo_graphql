package com.shahinbasahr.apollo.getitem

import android.os.Parcelable
import com.shahinbasahr.apollo.getitem.model.Item
import com.shahinbasahr.apollo.getitem.model.ItemListResult
import kotlinx.parcelize.Parcelize

@kotlinx.android.parcel.Parcelize
data class ItemListResult(
    val totalResult: Int = 0,
    val items: List<Item> = arrayListOf()
): Parcelable