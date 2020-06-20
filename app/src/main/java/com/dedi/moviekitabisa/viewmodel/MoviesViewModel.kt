package com.dedi.moviekitabisa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dedi.moviekitabisa.BuildConfig
import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.MovieRespone
import com.dedi.moviekitabisa.repository.ApiCallback


class MoviesViewModel(val moviesCallback: ApiCallback) : ViewModel() {
    fun getMoviesPopular(): LiveData<MovieRespone> {
        return moviesCallback.getMoviesPopular(BuildConfig.API_KEY)
    }
    fun getMoviesTopRated(): LiveData<MovieRespone> {
        return moviesCallback.getMoviesTopRated(BuildConfig.API_KEY)
    }
    fun getMoviesNowPlaying(): LiveData<MovieRespone> {
        return moviesCallback.getMoviesNowPlaying(BuildConfig.API_KEY)
    }

    fun getMoviesIdDetail(id_movie : Int): LiveData<DetailRespone> {
        return moviesCallback.getMoviesIdDetail(id_movie,BuildConfig.API_KEY)
    }

}