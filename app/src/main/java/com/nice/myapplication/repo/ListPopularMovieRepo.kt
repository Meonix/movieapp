package com.nice.myapplication.repo

import com.nice.app_ex.data.api.Api
import com.nice.app_ex.domain.base.Response
import com.nice.app_ex.domain.base.ResponseError
import com.nice.myapplication.model.ListPopularMovie
import retrofit2.Call

class ListPopularMovieRepo(private val mApi: Api) {
    suspend fun getPoppularMovie(page :Int): Response<ListPopularMovie> {
        return try {
            Response.success(mApi.getPoppularMovie(page))
        } catch (ex:Exception) {
            Response.error(ResponseError(101,ex.message.toString()))
        }
    }
}