package com.dedi.moviekitabisa.data

import android.os.Parcelable
import com.dedi.moviekitabisa.data.entity.Movie
import com.dedi.moviekitabisa.data.entity.ResultReview
import kotlinx.android.parcel.Parcelize


data class MovieRespone( val page: Int ,val results: List<Movie>, val total_results: Int, val total_pages: Int)

@Parcelize
open class DetailRespone(
    val id: Int,
    val poster_path: String,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val title: String,
    val original_title: String,
    val original_language: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float
): Parcelable

data class DetailReviewRespone(
    val id: Int,
    val page: Int,
    val results: List<ResultReview>,
    val total_pages: Int,
    val total_results: Int
)




