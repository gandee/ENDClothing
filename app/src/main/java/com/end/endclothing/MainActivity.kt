package com.end.endclothing


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.end.endclothing.api.CloudApiHelperImpl
import com.end.endclothing.api.RetrofitBuilder
import com.end.endclothing.model.Products
import kotlinx.android.synthetic.main.activity_main.*

import com.end.endclothing.ui.ENDClothingAdapter
import com.end.endclothing.ui.intent.ENDIntent
import com.end.endclothing.ui.state.EndState
import com.end.endclothing.util.ViewModelFactory
import com.end.endclothing.ui.viewmodel.ENDClothingViewModel
import kotlinx.coroutines.launch

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
        observeENDClosthingViewModel()
        getProduts()
    }

    private fun getProduts() {
        buttonFetchingENDProducts.setOnClickListener {
            lifecycleScope.launch {
                endClothingViewModel.userIntent.send(ENDIntent.getCombineProducts)
            }
        }
    }

    private fun observeENDClosthingViewModel() {
        lifecycleScope.launch {
            endClothingViewModel.state.collect {
                when (it) {
                    is EndState.Idle -> {

                    }
                    is EndState.Loading -> {
                        buttonFetchingENDProducts.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }

                    is EndState.Products -> {
                        progressBar.visibility = View.GONE
                        buttonFetchingENDProducts.visibility = View.GONE
                        displayProducts(it.products)
                    }
                    is EndState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetchingENDProducts.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun displayProducts(products: List<Products>) {
        recyclerView.visibility = View.VISIBLE
        products.let { listOfProducts -> listOfProducts.let { adapter.addProducts(products) } }
        adapter.notifyDataSetChanged()
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