package com.dedi.moviekitabisa.repository

import androidx.paging.DataSource
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel

import com.dedi.moviekitabisa.data.entity.ResultReview
import com.dedi.moviekitabisa.room.FavDao
import com.dedi.moviekitabisa.room.ReviewDao


class LocalRepository(private val favDao: FavDao,private val reviewDao: ReviewDao): LocalCallback {

    override fun getAllFavoriteDetail(): DataSource.Factory<Int, FavoriteDetailModel> {
        return favDao.getAllFavoriteDetail()
    }

    override fun getReview(id: Int): DataSource.Factory<Int, ResultReview> {
        return reviewDao.getReview(id)
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


    override fun deleteReview(favModel: ArrayList<ResultReview>) {
        return reviewDao.deleteReview(favModel)
    }


    override fun insertReview(favModel: ArrayList<ResultReview>) {
        return reviewDao.insertReview(favModel)
    }




//
//    override fun insertReviewResult(favModel: FavoriteReviewModelResult) {
//        return favDao.insertReviewResult(favModel)
//    }

}