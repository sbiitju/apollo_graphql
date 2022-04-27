package com.shahinbasahr.apollo.getitem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.observable
import com.shahinbasahr.apollo.getitem.network.GetItemRepo
import com.shahinbasahr.apollo.getitem.paging.DataSourceIMP
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetItemVIewModel @Inject constructor(
    private val dataSource: GetItemRepo
) : ViewModel() {
    private var totalResult = 20
    val list: ArrayList<String> = ArrayList()
    val result = MutableLiveData<ItemListResult>()
    val totalItems = MutableLiveData<Int>()

    val listData = Pager(PagingConfig(pageSize = 6)) {
        DataSourceIMP(dataSource)
    }.observable.cachedIn(viewModelScope)
}