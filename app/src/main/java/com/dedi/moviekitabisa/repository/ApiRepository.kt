package com.dedi.moviekitabisa.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dedi.moviekitabisa.BuildConfig
import com.dedi.moviekitabisa.api.ApiService
import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.DetailReviewRespone
import com.dedi.moviekitabisa.data.MovieRespone


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository : ApiCallback {
    val TAG = "ApiRepository"
    private val HTTP_API_SETUP_WIZART_URL = BuildConfig.API_URL
    private var apiService: ApiService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(HTTP_API_SETUP_WIZART_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    override fun getMoviesPopular(uid: String): LiveData<MovieRespone> {
        val data = MutableLiveData<MovieRespone>()
        apiService?.requestMoviePopularApi(uid)?.enqueue(object : Callback<MovieRespone> {
            override fun onResponse(call: Call<MovieRespone>, response: Response<MovieRespone>) {
                if (response.code() == 200 || response.isSuccessful) {
                    data.value = response.body()
                    Log.i(TAG, "code_responese"+response.code())
                }
            }

            override fun onFailure(call: Call<MovieRespone>, t: Throwable) {
                data.value=null
                Log.i(TAG, "code_responese null"+ t.printStackTrace())
            }
        })
        return data
    }

    override fun getMoviesTopRated(uid: String): LiveData<MovieRespone> {
        val data = MutableLiveData<MovieRespone>()
        apiService?.requestMovieTopRateApi(uid)?.enqueue(object : Callback<MovieRespone> {
            override fun onResponse(call: Call<MovieRespone>, response: Response<MovieRespone>) {
                if (response.code() == 200 || response.isSuccessful) {
                    data.value = response.body()
                    Log.i(TAG, "code_responese"+response.code())
                }
            }

            override fun onFailure(call: Call<MovieRespone>, t: Throwable) {
                data.value=null
                Log.i(TAG, "code_responese null"+ t.printStackTrace())
            }
        })
        return data
    }

    override fun getMoviesNowPlaying(uid: String): LiveData<MovieRespone> {
        val data = MutableLiveData<MovieRespone>()
        apiService?.requestMovieNowPlayingApi(uid)?.enqueue(object : Callback<MovieRespone> {
            override fun onResponse(call: Call<MovieRespone>, response: Response<MovieRespone>) {
                if (response.code() == 200 || response.isSuccessful) {
                    data.value = response.body()
                    Log.i(TAG, "code_responese"+response.code())
                }
            }

            override fun onFailure(call: Call<MovieRespone>, t: Throwable) {
                data.value=null
                Log.i(TAG, "code_responese null"+ t.printStackTrace())
            }
        })
        return data
    }

    override fun getMoviesIdDetail(id_detail: Int,uid: String): LiveData<DetailRespone> {
        val data = MutableLiveData<DetailRespone>()
        apiService?.requestMovieIdDetailApi(id_detail,uid)?.enqueue(object : Callback<DetailRespone> {
            override fun onResponse(call: Call<DetailRespone>, response: Response<DetailRespone>) {
                if (response.code() == 200 || response.isSuccessful) {
                    data.value = response.body()
                    Log.i(TAG, "code_responese getMoviesIdDetail "+response.code())
                }
            }

            override fun onFailure(call: Call<DetailRespone>, t: Throwable) {
                data.value=null
                Log.i(TAG, "code_responese null"+ t.printStackTrace())
            }
        })
        return data
    }

    override fun getMoviesIdReviewDetail(id_detail: Int, uid: String): LiveData<DetailReviewRespone> {
        val data = MutableLiveData<DetailReviewRespone>()
        apiService?.requestMovieIdReviewDetailApi(id_detail,uid)?.enqueue(object : Callback<DetailReviewRespone> {
            override fun onResponse(call: Call<DetailReviewRespone>, response: Response<DetailReviewRespone>) {
                if (response.code() == 200 || response.isSuccessful) {
                    data.value = response.body()
                    Log.i(TAG, "code_responese getMoviesIdReviewDetail "+response.code())
                }
            }

            override fun onFailure(call: Call<DetailReviewRespone>, t: Throwable) {
                data.value=null
                Log.i(TAG, "getMoviesIdReviewDetail null"+ t.printStackTrace())
            }
        })
        return data
    }


}


