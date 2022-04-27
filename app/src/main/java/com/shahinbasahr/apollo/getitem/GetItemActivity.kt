package com.shahinbasahr.apollo.getitem

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shahinbasahr.apollo.databinding.ActivityGetItemBinding
import com.shahinbasahr.apollo.getitem.paging.GetItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetItemBinding
    private lateinit var viewmodel: GetItemVIewModel
    private lateinit var getItemAdapter: GetItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getItemAdapter = GetItemAdapter()
        viewmodel = ViewModelProvider(this)[GetItemVIewModel::class.java]
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        setupList()
        setupView()
    }

    private fun setupList() {
        binding.itemViews.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = getItemAdapter
        }

    }

    @SuppressLint("CheckResult")
    private fun setupView() {
        viewmodel.listData.subscribe {
            Log.d("ListData", it.toString())
            getItemAdapter.submitData(lifecycle, it)
        }
    }
}