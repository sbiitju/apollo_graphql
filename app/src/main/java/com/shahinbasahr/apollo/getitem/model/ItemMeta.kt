package com.shahinbasahr.apollo.getitem.model

import android.os.Parcelable
import com.shahinbashar.apollo.MenuItemsQuery
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemMeta(
    val name: String? = null,
    val description: String? = null,
    val thumbnail: String? = null,
    val isSpicy: Boolean = false,
    val isVeg: Boolean = false,
    val isOrganic: Boolean = false,
    val calorie: Double = 0.0
):Parcelable {
    companion object {
        fun fromGQLItemList(meta: MenuItemsQuery.Meta?): ItemMeta {
            return ItemMeta(
                name = meta?.name,
                description = meta?.description,
                thumbnail = meta?.images?.thumbnail as String?,
                isSpicy = meta?.isSpicy ?: false,
                isVeg = meta?.isVeg ?: false
                )
        }

    }
}