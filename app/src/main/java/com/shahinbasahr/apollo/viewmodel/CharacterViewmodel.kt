package com.shahinbasahr.apollo.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.shahinbasahr.apollo.network.BaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterViewmodel @Inject constructor(
    private val baseRepo: BaseRepo
) : ViewModel() {
    @SuppressLint("CheckResult")
    fun getData() {
        baseRepo.getZone(23.8103, 90.4125)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(({
                Log.d("Check", it.getZone?.result.toString())
            }), ({
                Log.d("Check", it.cause.toString())

            }))
    }

}