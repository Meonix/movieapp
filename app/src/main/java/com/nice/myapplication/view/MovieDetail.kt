package com.nice.myapplication.view

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.nice.app_ex.data.api.POSTER_BASE_URL
import com.nice.myapplication.R
import com.nice.myapplication.SlidePagerAdapter
import com.nice.myapplication.viewModel.MainViewModel
import com.squareup.okhttp.OkHttpClient
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Console
import kotlin.concurrent.fixedRateTimer

class MovieDetail : AppCompatActivity() {
    private val myViewModel : MainViewModel by viewModel()
    private lateinit var tv : TextView
    private lateinit var iv : ImageView
    private lateinit var poster_path:String
    private var listSlides :MutableList<String> = ArrayList()
    internal lateinit var slidePager: ViewPager
    private var slidePagerAdapter: SlidePagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initViewMovieDetail()
        val movie_id:String = intent.extras!!["movie_id"].toString()
        poster_path= intent.extras!!["poster_path"].toString()
        setupViewModel(movie_id)


    }
    @SuppressLint("ResourceAsColor")
    private fun initViewMovieDetail(){
//        val mActionBar: ActionBar? = supportActionBar
//        mActionBar!!.setBackgroundDrawable(ColorDrawable(R.color.transParent))
        slidePager = findViewById(R.id.vpMovieDetail)
        tv = findViewById(R.id.tvTitleMovieDetail)
        iv = findViewById(R.id.ivMovieDetail)

    }
    private fun setupViewModel(movie_id : String){
        myViewModel.getMovie(movie_id.toInt())
        myViewModel.getData.observe(this, Observer {
            tv.text = it.title

            val picasso: Picasso
            val okHttpClient: OkHttpClient
            okHttpClient = OkHttpClient()
            picasso = Picasso.Builder(applicationContext)
                .downloader(OkHttpDownloader(okHttpClient))
                .build()
            picasso.load(poster_path).into(iv)
            listSlides.add(POSTER_BASE_URL + it.backdrop_path)
            listSlides.add(POSTER_BASE_URL + it.backdrop_path)
            listSlides.add(POSTER_BASE_URL + it.backdrop_path)
            slidePagerAdapter = SlidePagerAdapter(this,listSlides)
            slidePager.adapter = slidePagerAdapter

            tlMovieDetail.setupWithViewPager(slidePager,true)
            fixedRateTimer(name = "timer",initialDelay = 4000,period = 6000){
              runOnUiThread {
                  if(slidePager.currentItem < listSlides.count() - 1){
                      slidePager.currentItem = slidePager.currentItem+1
                  }
                  else {
                      slidePager.currentItem = 0
                  }
              }
            }
        })

    }
}
