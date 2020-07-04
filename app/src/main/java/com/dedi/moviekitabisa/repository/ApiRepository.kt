package com.dedi.moviekitabisa.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dedi.moviekitabisa.BuildConfig
import com.dedi.moviekitabisa.api.ApiClient
import com.dedi.moviekitabisa.api.ApiService
import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.DetailReviewRespone
import com.dedi.moviekitabisa.data.MovieRespone
import com.dedi.moviekitabisa.data.entity.Movie
import com.dedi.moviekitabisa.data.entity.ResultReview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.isNotEmpty as isNotEmpty

class ApiRepository : ApiCallback {
    val TAG = "ApiRepository"
    private var apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)

//    init {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(HTTP_API_SETUP_WIZART_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//        apiService = retrofit.create(ApiService::class.java)
//    }

//    override suspend fun getMoviesPopular(uid: String): LiveData<MovieRespone> {
//        val data = MutableLiveData<MovieRespone>()
//                try {
//                    data.value = apiService.requestMoviePopularApi(1,BuildConfig.API_KEY)
//                } catch (e: Throwable) {
//                    println("deditian errror repo ${e.printStackTrace()}")
//                }
//        return data
//    }

    override fun getMoviesTopRated(uid: String): LiveData<MovieRespone> {
        val data = MutableLiveData<MovieRespone>()
        apiService.requestMovieTopRateApi(uid).enqueue(object : Callback<MovieRespone> {
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
//        apiService.requestMovieNowPlayingApi(uid).enqueue(object : Callback<MovieRespone> {
//            override fun onResponse(call: Call<MovieRespone>, response: Response<MovieRespone>) {
//                if (response.code() == 200 || response.isSuccessful) {
//                    data.value = response.body()
//                    Log.i(TAG, "code_responese"+response.code())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieRespone>, t: Throwable) {
//                data.value=null
//                Log.i(TAG, "code_responese null"+ t.printStackTrace())
//            }
//        })
        return data
    }

    override fun getMoviesIdDetail(id_detail: Int,uid: String): LiveData<DetailRespone> {
        val data = MutableLiveData<DetailRespone>()
        apiService.requestMovieIdDetailApi(id_detail,uid).enqueue(object : Callback<DetailRespone> {
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
        apiService.requestMovieIdReviewDetailApi(id_detail,uid).enqueue(object : Callback<DetailReviewRespone> {
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

    override fun getMoviesIdReviewResultDetail(id_detail: Int, uid: String): LiveData<ResultReview> {
        val data = MutableLiveData<ResultReview>()
        apiService.requestMovieIdReviewResultDetailApi(id_detail,uid).enqueue(object : Callback<ResultReview> {
            override fun onResponse(call: Call<ResultReview>, response: Response<ResultReview>) {
                if (response.code() == 200 || response.isSuccessful) {
                    data.value = response.body()
                    Log.i(TAG, "code_responese ResultReview "+response.code())
                }
            }

            override fun onFailure(call: Call<ResultReview>, t: Throwable) {
                data.value=null
                Log.i(TAG, "getMoviesIdReviewDetail null"+ t.printStackTrace())
            }
        })
        return data
    }


}


