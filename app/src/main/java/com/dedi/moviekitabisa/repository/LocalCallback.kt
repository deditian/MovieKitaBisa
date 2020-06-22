package com.dedi.moviekitabisa.repository


import androidx.paging.DataSource
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel
import com.dedi.moviekitabisa.data.entity.ResultReview


interface LocalCallback {
    fun insertDetail(favModel: FavoriteDetailModel)
    fun deleteFavoriteDetail(favModel: FavoriteDetailModel)
    fun insertReview(favModel: ArrayList<ResultReview>)
    fun deleteReview(favModel: ArrayList<ResultReview>)

//    fun insertReviewResult(favModel: FavoriteReviewModelResult)

    fun getAllFavoriteDetail(): DataSource.Factory<Int, FavoriteDetailModel>


    fun getReview(id :Int): DataSource.Factory<Int, ResultReview>

    fun getAllFavoriteDetailID(id :Int): DataSource.Factory<Int, FavoriteDetailModel>


}