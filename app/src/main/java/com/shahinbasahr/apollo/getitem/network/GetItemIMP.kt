package com.shahinbasahr.apollo.getitem.network

import android.content.Context
import com.shahinbasahr.apollo.getitem.ItemListResult
import com.shahinbasahr.apollo.getitem.model.Item
import com.shahinbasahr.apollo.location.onException
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject

class GetItemIMP @Inject constructor(
    private val remoteSource: GetItemRepoNetwork,
    @ApplicationContext private val context: Context
) : GetItemRepo {
    override fun loadMenuItems(
        outletId: String,
        skip: Int,
        limit: Int,
        ): Observable<ItemListResult> {
        return remoteSource.loadMenuItems(outletId, skip, limit)
            .onException(context)
            .map {
                ItemListResult(
                    totalResult = it.getItems?.result?.count ?: 0,
                    items = it.getItems?.result?.items?.map { item ->
                        Item.fromGQLItemList(
                            item,
                            "",
                            0
                        )
                    } ?: arrayListOf()
                )

            }
    }
}