package com.nice.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nice.myapplication.viewModel.MainViewModel
import com.nice.myapplication.R
import com.nice.myapplication.model.Result
import com.nice.myapplication.view.adapter.PopularMovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private var page = 1
    private var isLoading = true
    private var limit : Int?= 0
    private lateinit var progressBar : ProgressBar
    private val myViewModel : MainViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapterView : PopularMovieAdapter
    private val list :MutableList<Result> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setupViewModel()
    }
    private fun initView(){
        recyclerView = findViewById(R.id.rvPopularMovie)
        linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.setHasFixedSize(true)

        progressBar = findViewById(R.id.mainProgressBar)
    }
    private fun setupViewModel() {
        myViewModel.getListPopularMovie(page)
        myViewModel.getData1.observe(this, Observer {
            list.addAll(it.results)
            recyclerView.adapter!!.notifyDataSetChanged()
        })
        adapterView = PopularMovieAdapter(this@MainActivity,list, this)
        limit = adapterView.itemCount
        recyclerView.adapter = adapterView


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dx > 0) {
                val visibleItemCount = linearLayoutManager.childCount
                val pastVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapterView.itemCount
                if (isLoading) {
                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        page += 1
                        getPage()
                        isLoading = false
                    }
                }
//              }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    fun getPage() {
        isLoading = true
        progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
                myViewModel.getListPopularMovie(page)
                myViewModel.getData1.observe(this, Observer {
//                recyclerView.adapter!!.notifyDataSetChanged()
                })
//                adapterView.notifyDataSetChanged()
//                recyclerView.adapter = adapterView
            progressBar.visibility = View.GONE

            isLoading = true
        }, 1200)


    }
}
