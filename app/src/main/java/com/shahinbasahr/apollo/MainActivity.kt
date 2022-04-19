package com.shahinbasahr.apollo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shahinbasahr.apollo.maps.MapsActivity
import com.shahinbasahr.apollo.view.state.ViewState
import com.shahinbasahr.apollo.viewmodel.CharacterViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel = ViewModelProvider(this)[CharacterViewmodel::class.java]
        viewModel.getData()
    }

    fun GOTOMAP(view: View) {
        startActivity(Intent(this,MapsActivity::class.java))
    }
}