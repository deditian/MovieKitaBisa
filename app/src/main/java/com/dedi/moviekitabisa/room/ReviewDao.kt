package com.dedi.moviekitabisa.room

import androidx.paging.DataSource
import androidx.room.*
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel
import com.dedi.moviekitabisa.data.entity.ResultReview

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReview(favModel: ArrayList<ResultReview>)

    @Query("SELECT * from favorite_review_table  WHERE movieId IN (:id)")
    fun getReview(id : Int): DataSource.Factory<Int, ResultReview>

    @Delete
    fun deleteReview(favModel: ArrayList<ResultReview>)
}