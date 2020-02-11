package com.nice.app_ex.data.di

import com.nice.myapplication.repo.ListPopularMovieRepo
import com.nice.myapplication.repo.MovieRepo
import org.koin.dsl.module

val repositoryModule = module {
      single { MovieRepo(get())}
      single { ListPopularMovieRepo(get()) }
}