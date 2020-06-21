package com.dedi.moviekitabisa.repository


import androidx.paging.DataSource
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel

import com.dedi.moviekitabisa.data.entity.FavoriteReviewModel
import com.dedi.moviekitabisa.data.entity.FavoriteReviewModelResult

interface LocalCallback {
    fun insertDetail(favModel: FavoriteDetailModel)
    fun deleteFavoriteDetail(favModel: FavoriteDetailModel)
//    fun insertReview(favModel: FavoriteReviewModel)
//    fun insertReviewResult(favModel: FavoriteReviewModelResult)

    fun getAllFavoriteDetail(): DataSource.Factory<Int, FavoriteDetailModel>

    fun getAllFavoriteDetailID(id :Int): DataSource.Factory<Int, FavoriteDetailModel>


}