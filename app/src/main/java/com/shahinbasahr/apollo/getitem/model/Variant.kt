package com.shahinbasahr.apollo.getitem.model

import android.os.Parcelable
import com.shahinbashar.apollo.MenuItemsQuery
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Variant(
    val id: String,
    val name: String? = null,
    val price: Double = 0.0,
    val additionalPrice: Double = 0.0,
    val preparationTime: Int = 0,
    val isToppingAvailable: Boolean = false,
    val proportion: String? = null,
    var discountedPrice: Double? = null
):Parcelable {
    companion object {
        fun fromGQLItemList(variant: MenuItemsQuery.Variant?): Variant {
            return Variant(
                id = (variant?.id ?: "") as String,
                name = variant?.name,
                price = variant?.price ?: 0.0,
                additionalPrice = variant?.additionalPrice ?: 0.0,
                preparationTime = variant?.preparationTime ?: 0,
                isToppingAvailable = variant?.isToppingAvailable ?: false,
                proportion = variant?.proportion,
                discountedPrice = variant?.discountedPrice
            )
        }

    }
}
