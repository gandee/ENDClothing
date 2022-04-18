package com.end.endclothing


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.end.endclothing.api.CloudApiHelperImpl
import com.end.endclothing.api.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*

import com.end.endclothing.ui.ENDClothingAdapter
import com.end.endclothing.util.ViewModelFactory
import com.end.endclothing.viewmodel.ENDClothingViewModel

/**
 * Created by GandeepanS on 18/04/2022.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var endClothingViewModel: ENDClothingViewModel
    private var adapter = ENDClothingAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycleView()
        setupViewModel()
    }

    private fun setupViewModel() {
        endClothingViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                CloudApiHelperImpl(
                    RetrofitBuilder.cloudApiService
                )
            )
        ).get(ENDClothingViewModel::class.java)
    }

    private fun setupRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }
}