package com.shahinbasahr.apollo.getitem.network

import android.content.Context
import com.apollographql.apollo.rx2.Rx2Apollo
import com.shahinbasahr.apollo.location.onResponse
import com.shahinbasahr.apollo.network.ProvideApolloClient
import com.shahinbashar.apollo.MenuItemsQuery
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject

class GetItemGraphQL @Inject constructor(@ApplicationContext private val context: Context) :GetItemRepoNetwork{
    override fun loadMenuItems(
        outletId: String,
        skip: Int,
        limit: Int,
    ): Observable<MenuItemsQuery.Data> {
        val query = MenuItemsQuery(skip,limit,outletId)
        return Rx2Apollo.from(ProvideApolloClient.getApolloClient(context) .query(query)).onResponse()
    }

}