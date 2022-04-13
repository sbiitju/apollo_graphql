package com.shahinbasahr.apollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shahinbasahr.apollo.viewmodel.CharacterViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel=ViewModelProvider(this)[CharacterViewmodel::class.java]
        viewModel.queryCharactersList()
        viewModel.charactersList.observe(this, Observer {
            Log.d("Check",it.value.toString())
        })
    }
}