package com.dedi.moviekitabisa.data.entity

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class Movie(
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
) : Parcelable