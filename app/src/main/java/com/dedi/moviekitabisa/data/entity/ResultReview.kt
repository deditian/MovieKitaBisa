package com.dedi.moviekitabisa.data.entity

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class ResultReview(
    val id: String,
    val author: String,
    val content: String,
    val url: String
) : Parcelable