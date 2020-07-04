package com.dedi.moviekitabisa.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.dedi.moviekitabisa.BuildConfig
import com.dedi.moviekitabisa.api.ApiClient
import com.dedi.moviekitabisa.api.ApiService
import com.dedi.moviekitabisa.data.entity.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PopularDataSource(coroutineContext: CoroutineContext):PageKeyedDataSource<Int, Movie>(){

    private var apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial( params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        scope.launch {
            try {
                val responseMovie = apiService.requestMoviePopularApi(1)
                when{
                    responseMovie.isSuccessful -> {
                        val listing = responseMovie.body()?.results
                        println("deditian listing  ${listing?.lastIndex.toString()} $listing ")
                        val result = listing?.map { it }
                        callback.onResult(result ?: listOf(), null,2 )
                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        scope.launch {
            try {
                val responseMovie = apiService.requestMoviePopularApi(params.key)
                when{
                    responseMovie.isSuccessful -> {
                        val listing = responseMovie.body()?.results
                        val items = listing?.map { it }
                        println("deditian params.ke ${params.key+1} ${listing?.lastIndex.toString()}")
                        callback.onResult(items ?: listOf(),params.key+1)
                    }
                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
//        scope.launch {
//            try {
//                val response =
//                    apiService.requestMoviePopularApi(params.key)
//                when{
//                    response.isSuccessful -> {
//                        val listing = response.body()?.results
//                        val items = listing?.map { it }
//                        println("deditian ${listing?.lastIndex.toString()}")
//                        callback.onResult(items ?: listOf(), null)
//                    }
//                }
//
//            }catch (exception : Exception){
//                Log.e("PostsDataSource", "Failed to fetch data!")
//            }
//        }
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

}