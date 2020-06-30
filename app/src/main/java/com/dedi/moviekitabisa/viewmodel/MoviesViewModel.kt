package com.dedi.moviekitabisa.viewmodel

import androidx.lifecycle.*


import com.dedi.moviekitabisa.BuildConfig

import com.dedi.moviekitabisa.data.MovieRespone
import com.dedi.moviekitabisa.data.entity.Movie

import com.dedi.moviekitabisa.repository.ApiCallback
import es.dmoral.toasty.Toasty.success
import kotlinx.coroutines.Dispatchers
import retrofit2.Response.success
import kotlin.Result.Companion.success


class MoviesViewModel(private val moviesCallback: ApiCallback) : ViewModel() {




    fun getMoviesPopular() : LiveData<MovieRespone> = liveData {
        val retrivedTodo  = moviesCallback.getMoviesPopular(BuildConfig.API_KEY)
        try {
            emitSource(retrivedTodo)
        } catch (e: Exception) {
            e.localizedMessage
//            emit(Result.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


//    suspend fun getMoviesPopular(): LiveData<MovieRespone> {
//        return moviesCallback.getMoviesPopular(BuildConfig.API_KEY)
//    }
    fun getMoviesTopRated(): LiveData<MovieRespone> {
        return moviesCallback.getMoviesTopRated(BuildConfig.API_KEY)
    }
    fun getMoviesNowPlaying(): LiveData<MovieRespone> {
        return moviesCallback.getMoviesNowPlaying(BuildConfig.API_KEY)
    }



}