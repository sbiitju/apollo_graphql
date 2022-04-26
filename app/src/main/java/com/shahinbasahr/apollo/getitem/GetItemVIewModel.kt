package com.shahinbasahr.apollo.getitem

import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.observable
import com.shahinbasahr.apollo.getitem.model.Item
import com.shahinbasahr.apollo.getitem.network.GetItemRepo
import com.shahinbasahr.apollo.getitem.paging.DataSourceIMP
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
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
    fun getData(){
        val disposable = dataSource.loadMenuItems(
            outletId = "5cc7f78009472269fe3ca490",
            skip = 1,
            limit = 20,
        ).subscribe(({

            result.postValue(it)
        }), ({
        }))

    }

}