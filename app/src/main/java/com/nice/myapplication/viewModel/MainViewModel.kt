package com.nice.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nice.app_ex.base.BaseViewModel
import com.nice.app_ex.domain.base.onFailure
import com.nice.app_ex.domain.base.onLoading
import com.nice.app_ex.domain.base.onSuccess
import com.nice.myapplication.model.ListPopularMovie
import com.nice.myapplication.model.Movie
import com.nice.myapplication.repo.ListPopularMovieRepo
import com.nice.myapplication.repo.MovieRepo

class MainViewModel (private val mMovieRepo:MovieRepo,private val mListPopularMovie: ListPopularMovieRepo): BaseViewModel(){

    private val _getData = MutableLiveData<Movie>()

    val getData:LiveData<Movie> get() = _getData

     fun getMovie(movie_id: Int) = executeUseCase {
         mMovieRepo.getMovie(movie_id)
            .onLoading {
                println("Loading $it")
            }

            .onSuccess {
                _getData.value = it
            }

            .onFailure {
                println("err $it")
            }
    }

    private val _getData1 = MutableLiveData<ListPopularMovie>()
    val getData1: LiveData<ListPopularMovie> get() = _getData1
    fun getListPopularMovie(page: Int) = executeUseCase {
        mListPopularMovie.getPoppularMovie(page)
            .onLoading {
                println("Loading $it")
            }

            .onSuccess {
                _getData1.value = it
            }

            .onFailure {
                println("err $it")
            }
    }

}