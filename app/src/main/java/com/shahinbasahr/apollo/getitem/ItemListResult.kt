package com.shahinbasahr.apollo.getitem

import com.shahinbasahr.apollo.getitem.model.Item
import com.shahinbasahr.apollo.getitem.model.ItemListResult

data class ItemListResult(
    val totalResult: Int = 0,
    val items: List<Item> = arrayListOf()
) : Collection<ItemListResult> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: ItemListResult): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<ItemListResult>): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<ItemListResult> {
        TODO("Not yet implemented")
    }
}
