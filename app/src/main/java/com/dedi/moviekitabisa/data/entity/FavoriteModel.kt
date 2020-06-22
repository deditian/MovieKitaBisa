package com.dedi.moviekitabisa.data.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_detail_table")
data class FavoriteDetailModel(
    @PrimaryKey
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("poster_path") val poster_path: String,
    @field:SerializedName("adult") val adult: Boolean,
    @field:SerializedName("overview") val overview: String,
    @field:SerializedName("release_date") val release_date: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("original_title") val original_title: String,
    @field:SerializedName("original_language")  val original_language: String,
    @field:SerializedName("backdrop_path") val backdrop_path: String?,
    @field:SerializedName("popularity") val popularity: Float,
    @field:SerializedName("vote_count") val vote_count: Int,
    @field:SerializedName("video") val video: Boolean,
    @field:SerializedName("vote_average") val vote_average: Float
):Parcelable
