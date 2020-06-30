package com.dedi.moviekitabisa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dedi.moviekitabisa.BuildConfig
import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.DetailReviewRespone
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel

import com.dedi.moviekitabisa.data.entity.ResultReview
import com.dedi.moviekitabisa.repository.ApiCallback
import com.dedi.moviekitabisa.repository.LocalCallback


class DetailViewModel(private val moviesCallback: ApiCallback, private val localCallback: LocalCallback) : ViewModel() {


    fun getMoviesIdReviewDetail(id_movie : Int): LiveData<DetailReviewRespone> {
        return moviesCallback.getMoviesIdReviewDetail(id_movie, BuildConfig.API_KEY)
    }

    fun getAllFavoriteDetailID(int: Int): LiveData<PagedList<FavoriteDetailModel>> {
        println("deditian FavoriteViewModel getAllFavoriteDetailID $int ")
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20).build()

        return LivePagedListBuilder(localCallback.getAllFavoriteDetailID(int), pagedListConfig).build()
    }

    fun insertDetail(favModel: FavoriteDetailModel) {
        println("deditian insertDetail  favModel $favModel")
        localCallback.insertDetail(favModel)
    }

    fun deleteFavoriteDetail(favModel: FavoriteDetailModel) {
        println("deditian insertDetail  favModel $favModel")
        localCallback.deleteFavoriteDetail(favModel)
    }

    fun getReview(id :Int): LiveData<PagedList<ResultReview>> {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20).build()

        return LivePagedListBuilder(localCallback.getReview(id), pagedListConfig).build()
    }

    fun insertReview(reviews: ArrayList<ResultReview>, movieId: Int) {
        for (review in reviews!!) {
            review.movieId = movieId
        }
        localCallback.insertReview(reviews)
    }

    fun deleteReview(reviews: ArrayList<ResultReview>) {
        localCallback.deleteReview(reviews)
    }


}