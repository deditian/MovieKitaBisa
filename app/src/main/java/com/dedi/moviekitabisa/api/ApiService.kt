package com.dedi.moviekitabisa.api



import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.DetailReviewRespone
import com.dedi.moviekitabisa.data.MovieRespone
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun requestMoviePopularApi(
        @Query("api_key") api_key: String
    ): Call<MovieRespone>

    @GET("movie/top_rated")
    fun requestMovieTopRateApi(
        @Query("api_key") api_key: String
    ): Call<MovieRespone>

    @GET("movie/now_playing")
    fun requestMovieNowPlayingApi(
        @Query("api_key") api_key: String
    ): Call<MovieRespone>

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



}
