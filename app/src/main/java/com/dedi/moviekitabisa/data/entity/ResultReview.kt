package com.dedi.moviekitabisa.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_review_table")
data class ResultReview(
    @PrimaryKey
    @field:SerializedName("id") val id: String,
    @field:SerializedName("movie_id")var movieId: Int = 0,
    @field:SerializedName("author") val author: String? = null,
    @field:SerializedName("content") val content: String? = null,
    @field:SerializedName("url") val url: String? = null
) : Parcelable