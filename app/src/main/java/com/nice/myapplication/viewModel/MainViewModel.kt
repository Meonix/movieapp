package com.nice.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nice.app_ex.base.BaseViewModel
import com.nice.app_ex.domain.base.onFailure
import com.nice.app_ex.domain.base.onLoading
import com.nice.app_ex.domain.base.onSuccess
import com.nice.myapplication.model.ListImage
import com.nice.myapplication.model.ListPopularMovie
import com.nice.myapplication.model.Movie
import com.nice.myapplication.repo.ListImageRepo
import com.nice.myapplication.repo.ListPopularMovieRepo
import com.nice.myapplication.repo.MovieRepo

class MainViewModel (private val mMovieRepo:MovieRepo,
                     private val mListPopularMovie: ListPopularMovieRepo,
                     private val mListImage: ListImageRepo): BaseViewModel(){

    private val _getDataMovieDetail = MutableLiveData<Movie>()

    val getDataMovieDetail:LiveData<Movie> get() = _getDataMovieDetail

     fun getMovie(movie_id: Int) = executeUseCase {
         mMovieRepo.getMovie(movie_id)
            .onLoading {
                println("Loading $it")
            }

            .onSuccess {
                _getDataMovieDetail.value = it
            }

            .onFailure {
                println("err $it")
            }
    }

    private val _getListPopularMovie = MutableLiveData<ListPopularMovie>()
    val getListPopularMovie: LiveData<ListPopularMovie> get() = _getListPopularMovie
    fun getListPopularMovie(page: Int) = executeUseCase {
        mListPopularMovie.getPoppularMovie(page)
            .onLoading {
                println("Loading $it")
            }

            .onSuccess {
                _getListPopularMovie.value = it
            }

            .onFailure {
                println("err $it")
            }
    }


    private val _getListImageMovie = MutableLiveData<ListImage>()
    val getListImageMovie: LiveData<ListImage> get() = _getListImageMovie
    fun getListImageMovie(movie_id: Int) = executeUseCase {
        mListImage.getListImage(movie_id)
            .onLoading {
                println("Loading $it")
            }

            .onSuccess {
                _getListImageMovie.value = it
            }

            .onFailure {
                println("err $it")
            }
    }
}