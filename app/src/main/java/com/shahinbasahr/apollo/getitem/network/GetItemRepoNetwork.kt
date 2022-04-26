package com.shahinbasahr.apollo.getitem.network

import com.shahinbashar.apollo.MenuItemQuery
import com.shahinbashar.apollo.MenuItemsQuery
import io.reactivex.Observable

interface GetItemRepoNetwork {
    fun loadMenuItems(
        outletId: String,
        skip: Int,
        limit: Int,
    ): Observable<MenuItemsQuery.Data>
}