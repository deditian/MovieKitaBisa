package com.dedi.moviekitabisa.viewmodel

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList


import com.dedi.moviekitabisa.BuildConfig

import com.dedi.moviekitabisa.data.MovieRespone
import com.dedi.moviekitabisa.data.entity.Movie
import com.dedi.moviekitabisa.datasource.PopularDataSource

import com.dedi.moviekitabisa.repository.ApiCallback
import es.dmoral.toasty.Toasty.success
import kotlinx.coroutines.Dispatchers
import retrofit2.Response.success
import kotlin.Result.Companion.success


class MoviesViewModel(private val moviesCallback: ApiCallback) : ViewModel() {

    var postsLiveData  :LiveData<PagedList<Movie>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPopular():LiveData<PagedList<Movie>> = postsLiveData
    fun getNowPlaying():LiveData<PagedList<Movie>> = postsLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Movie> {

        val dataSourceFactory = object : DataSource.Factory<Int, Movie>() {
            override fun create(): DataSource<Int, Movie> {
                return PopularDataSource(Dispatchers.IO)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }







//    fun getMoviesPopular() : LiveData<MovieRespone> = liveData {
//        val retrivedTodo  = moviesCallback.getMoviesPopular(BuildConfig.API_KEY)
//        try {
//            emitSource(retrivedTodo)
//        } catch (e: Exception) {
//            e.localizedMessage
////            emit(Result.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }


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