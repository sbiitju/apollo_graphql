package com.shahinbasahr.apollo.view.fragment.showrestuarentlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shahinbasahr.apollo.R
import com.shahinbasahr.apollo.databinding.FragmentShowRestaurantListBinding
import com.shahinbasahr.apollo.nav_activity.NavActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowRestaurantList : Fragment() {

    private lateinit var viewModel: ShowRestaurantListViewModel
    private lateinit var binding: FragmentShowRestaurantListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ShowRestaurantListViewModel::class.java]
        // Inflate the layout for this fragment
        binding= FragmentShowRestaurantListBinding.inflate(inflater, container, false)
        binding.viewmodel=viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.location.postValue( (activity as? NavActivity)?.userLocation)
    }

}