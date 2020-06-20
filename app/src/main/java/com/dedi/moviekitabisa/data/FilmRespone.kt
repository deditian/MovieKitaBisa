package com.dedi.moviekitabisa.data


import com.dedi.moviekitabisa.data.entity.Movie

data class MovieRespone( val results: List<Movie>)

data class DetailRespone(
    val poster_path: String,
    val adult: Boolean,
    val overview: String,
    val release_date: String,
    val title: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_title: String,
    val original_language: String,
    val backdrop_path: String?,
    val popularity: Float,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Float
)
