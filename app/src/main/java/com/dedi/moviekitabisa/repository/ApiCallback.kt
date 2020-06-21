package com.dedi.moviekitabisa.repository

import androidx.lifecycle.LiveData
import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.DetailReviewRespone
import com.dedi.moviekitabisa.data.MovieRespone
import com.dedi.moviekitabisa.data.entity.ResultReview

interface ApiCallback {
    fun getMoviesPopular(uid: String): LiveData<MovieRespone>
    fun getMoviesTopRated(uid: String): LiveData<MovieRespone>
    fun getMoviesNowPlaying(uid: String): LiveData<MovieRespone>
    fun getMoviesIdDetail(id_detail: Int,uid: String): LiveData<DetailRespone>
    fun getMoviesIdReviewDetail(id_detail: Int,uid: String): LiveData<DetailReviewRespone>
    fun getMoviesIdReviewResultDetail(id_detail: Int,uid: String): LiveData<ResultReview>
}