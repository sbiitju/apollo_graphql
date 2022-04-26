package com.shahinbasahr.apollo.getitem.network

import com.shahinbasahr.apollo.getitem.ItemListResult
import com.shahinbashar.apollo.MenuItemQuery
import io.reactivex.Observable

interface GetItemRepo {
    fun loadMenuItems(
        outletId: String,
        skip: Int,
        limit: Int,
    ): Observable<ItemListResult>
}