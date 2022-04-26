package com.shahinbasahr.apollo.getitem.model

import com.shahinbashar.apollo.MenuItemsQuery

data class Item(
    val id: String,
    val basePrice: Double = 0.0,
    var totalFavorite: Int = 0,
    val meta: ItemMeta? = null,
    val restaurant: Restaurant? = null,
    val outletId: String,
    val outletName: String? = null,
    val variants: List<Variant> = arrayListOf(),
    var isFavorite: Boolean = false,
    var discountedPrice: Double? = null,
    var realPrice: Double? = null
) {
    var quantity: Int = 0
    var spicyIcon: String? = null

    companion object {
        fun fromGQLItemList(item: MenuItemsQuery.Item?, spicyIcon: String, qty: Int = 0): Item {
            return Item(
                id = (item?.id ?: "") as String,
                basePrice = item?.basePrice ?: 0.0,
                totalFavorite = item?.totalFavorite ?: 0,
                meta = ItemMeta.fromGQLItemList(item?.meta),
                outletId = (item?.outlet?.id ?: "") as String,
                outletName = item?.outlet?.name,
                isFavorite = item?.isFavorite ?: false,
                variants = item?.variants?.map {
                    Variant.fromGQLItemList(it)
                } ?: arrayListOf(),
                discountedPrice = item?.discountedPrice,
                realPrice = item?.realPrice
            ).apply {
                this.quantity = qty
                this.spicyIcon = spicyIcon
            }
        }


    }
}
