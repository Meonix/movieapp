package com.nice.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nice.app_ex.data.api.POSTER_BASE_URL
import com.nice.myapplication.model.Result
import com.nice.myapplication.ui.PopularMovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val myViewModel : MainViewModel by viewModel()
    private val list :MutableList<Result> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewModel.getMovie()
        myViewModel.getListPopularMovie(2)
        val recyclerView = findViewById<RecyclerView>(R.id.rvPopularMovie)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.setHasFixedSize(true)
        setupViewModel()
        myViewModel.getData1.observe(this, Observer {
            list.addAll(it.results)
            recyclerView.adapter!!.notifyDataSetChanged()
        })
        val adapterView = PopularMovieAdapter(list,this)
        recyclerView.adapter = adapterView
    }

    private fun setupViewModel() {
        myViewModel.getData.observe(this, Observer {
            //TO Do
        })
        myViewModel.getData1.observe(this, Observer {

        })
    }
}
