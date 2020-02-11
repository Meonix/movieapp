package com.nice.myapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nice.app_ex.data.api.POSTER_BASE_URL
import com.nice.myapplication.R
import com.nice.myapplication.model.Result
import com.squareup.okhttp.OkHttpClient
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso

class PopularMovieAdapter(private val popularMovieList: MutableList<Result>,val context: Context) :RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_main_layout,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return popularMovieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listPopularMovie= popularMovieList[position]
        holder.tvPopularMovie.text = listPopularMovie.originalTitle

        val moviePosterURL = POSTER_BASE_URL + listPopularMovie.posterPath

        //load image form https url into view holder (see build gradle)
        val picasso: Picasso
        val okHttpClient: OkHttpClient
        okHttpClient = OkHttpClient()
        picasso = Picasso.Builder(context)
            .downloader(OkHttpDownloader(okHttpClient))
            .build()
        picasso.load(moviePosterURL).placeholder(R.drawable.loading).into(holder.ivPopularMovie)
        //
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
            val tvPopularMovie = itemView.findViewById(R.id.tvMovie) as TextView
            val ivPopularMovie =itemView.findViewById(R.id.ivPopularMovie) as ImageView
    }
}