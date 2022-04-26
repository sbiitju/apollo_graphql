package com.shahinbasahr.apollo.getitem.model

import com.shahinbasahr.apollo.getitem.model.Item

data class ItemListResult(
    val totalResult: Int = 0,
    val items: List<Item> = arrayListOf()
)
