package com.dedi.moviekitabisa.repository

import androidx.paging.DataSource
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel
import com.dedi.moviekitabisa.data.entity.FavoriteReviewModel
import com.dedi.moviekitabisa.data.entity.FavoriteReviewModelResult
import com.dedi.moviekitabisa.room.FavDao


class LocalRepository(private val favDao: FavDao): LocalCallback {

    override fun getAllFavoriteDetail(): DataSource.Factory<Int, FavoriteDetailModel> {
        return favDao.getAllFavoriteDetail()
    }

    override fun getAllFavoriteDetailID(id :Int): DataSource.Factory<Int, FavoriteDetailModel> {
        return favDao.getAllFavoriteDetailID(id)
    }

    override fun insertDetail(favModel: FavoriteDetailModel) {
        return favDao.insertDetail(favModel)
    }

    override fun deleteFavoriteDetail(favModel: FavoriteDetailModel) {
        return favDao.deleteFavoriteDetail(favModel)
    }



//    override fun insertReview(favModel: FavoriteReviewModel) {
//        return favDao.insertReview(favModel)
//    }
//
//    override fun insertReviewResult(favModel: FavoriteReviewModelResult) {
//        return favDao.insertReviewResult(favModel)
//    }

}