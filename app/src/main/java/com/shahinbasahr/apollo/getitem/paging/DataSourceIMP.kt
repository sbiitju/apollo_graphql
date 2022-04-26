package com.shahinbasahr.apollo.getitem.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shahinbasahr.apollo.getitem.model.Item
import com.shahinbasahr.apollo.getitem.model.ItemListResult
import com.shahinbasahr.apollo.getitem.network.GetItemRepo
import com.shahinbasahr.apollo.location.onException


class DataSourceIMP(private val getItemRepo: GetItemRepo) : PagingSource<Int, Item>() {

    @SuppressLint("CheckResult")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = getItemRepo.loadMenuItems( outletId = "5cc7f78009472269fe3ca490",
                skip = 1,
                limit = 20)
            val responseData = mutableListOf<Item>()
            response.subscribe(({
             responseData.addAll(it.items)
            }),({
                Log.d("ErrorException",it.toString())
            }))

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        TODO("Not yet implemented")
    }
}