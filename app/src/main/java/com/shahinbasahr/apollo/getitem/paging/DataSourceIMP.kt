package com.shahinbasahr.apollo.getitem.paging

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.shahinbasahr.apollo.getitem.ItemListResult
import com.shahinbasahr.apollo.getitem.model.Item
import com.shahinbasahr.apollo.getitem.network.GetItemRepo
import io.reactivex.Single

const val ITEM_PAGE_LIMIT = 15

class DataSourceIMP(private val getItemRepo: GetItemRepo) : RxPagingSource<Int, Item>() {

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        TODO("Not yet implemented")
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Item>> {
        val currentLoadingPageKey = params.key ?: 1
        val nextKey = (currentLoadingPageKey + ITEM_PAGE_LIMIT)
        val previousKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - ITEM_PAGE_LIMIT
        return getItemRepo.loadMenuItems(
            outletId = "5cc7f78009472269fe3ca490",
            skip = currentLoadingPageKey,
            limit = ITEM_PAGE_LIMIT
        ).single(
            ItemListResult()
        ).map {
            LoadResult.Page(
                data = it.items,
                prevKey = previousKey,
                nextKey = nextKey
            )
        }
    }
}