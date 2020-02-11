package com.nice.app_ex.data.api

import com.nice.myapplication.model.ListPopularMovie
import com.nice.myapplication.model.Movie
import retrofit2.http.GET
const val POSTER_BASE_URL = "http://image.tmdb.org/t/p/w500"
interface Api {
      @GET("movie/76341?api_key=4e2895c2da6e111db3b75e57882e7ff5")
        suspend fun getMovie(): Movie
      @GET("movie/popular?api_key=4e2895c2da6e111db3b75e57882e7ff5")
        suspend fun getPoppularMovie(): ListPopularMovie
}