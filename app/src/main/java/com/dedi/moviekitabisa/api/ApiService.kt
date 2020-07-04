package com.dedi.moviekitabisa.api



import com.dedi.moviekitabisa.BuildConfig
import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.DetailReviewRespone
import com.dedi.moviekitabisa.data.MovieRespone
import com.dedi.moviekitabisa.data.entity.ResultReview
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun requestMoviePopularApi(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Response<MovieRespone>

    @GET("movie/top_rated")
    fun requestMovieTopRateApi(
        @Query("api_key") api_key: String
    ): Call<MovieRespone>

    @GET("movie/now_playing")
    suspend fun requestMovieNowPlayingApi(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): Response<MovieRespone>

    @GET("movie/{movie_id}")
    fun requestMovieIdDetailApi(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<DetailRespone>

    @GET("movie/{movie_id}/reviews")
    fun requestMovieIdReviewDetailApi(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<DetailReviewRespone>

    @GET("movie/{movie_id}/reviews")
    fun requestMovieIdReviewResultDetailApi(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Call<ResultReview>



}
