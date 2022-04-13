package com.shahinbasahr.apollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shahinbasahr.apollo.view.state.ViewState
import com.shahinbasahr.apollo.viewmodel.CharacterViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel=ViewModelProvider(this)[CharacterViewmodel::class.java]
        viewModel.getData()
//        viewModel.queryCharactersList()
//        viewModel.charactersList.observe(this, Observer {
//            when (it) {
//                is ViewState.Loading -> {
//                    Log.d("Check","Loading")
//
//                }
//                is ViewState.Success -> {
//                    Log.d("Check","Success "+it.value.toString())
//
//                }
//                is ViewState.Error -> {
//                    Log.d("Check","Error "+it.message.toString())
//                }
//            }
//        })
    }
}