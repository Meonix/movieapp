package com.nice.myapplication.repo

import com.nice.app_ex.data.api.Api
import com.nice.app_ex.domain.base.Response
import com.nice.app_ex.domain.base.ResponseError
import com.nice.myapplication.model.ListPopularMovie

class ListPopularMovieRepo(private val mApi: Api) {
    suspend fun getPoppularMovie(): Response<ListPopularMovie> {
        return try {
            Response.success(mApi.getPoppularMovie())
        } catch (ex:Exception) {
            Response.error(ResponseError(101,ex.message.toString()))
        }
    }
}