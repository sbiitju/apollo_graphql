package com.shahinbasahr.apollo.getitem

import android.annotation.SuppressLint
import android.os.Bundle
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

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getItemAdapter = GetItemAdapter()
        viewmodel = ViewModelProvider(this)[GetItemVIewModel::class.java]
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        setupView()
        setupList()

    }

    @SuppressLint("CheckResult")
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
            getItemAdapter.submitData(lifecycle, it)
        }
    }
}