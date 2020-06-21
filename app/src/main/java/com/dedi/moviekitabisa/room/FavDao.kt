package com.dedi.moviekitabisa.room

import androidx.paging.DataSource
import androidx.room.*
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel


@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(favModel: FavoriteDetailModel)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertReview(favModel: FavoriteReviewModel)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertReviewResult(favModel: FavoriteReviewModelResult)

    @Query("SELECT * from favorite_detail_table")
    fun getAllFavoriteDetail(): DataSource.Factory<Int, FavoriteDetailModel>

    @Query("SELECT * from favorite_detail_table WHERE id IN (:id)")
    fun getAllFavoriteDetailID(id : Int): DataSource.Factory<Int, FavoriteDetailModel>

//    @Query("SELECT * from favorite_detail_table WHERE id IN (:status) ORDER BY id ASC")
//    fun getAllTvShow(status : String): DataSource.Factory<Int, FavoriteDetailModel>
//
//    @Query("SELECT * FROM favorite_detail_table WHERE id IN (:id)")
//    fun getById(id: Int): DataSource.Factory<Int, FavoriteDetailModel>
//
    @Delete
    fun deleteFavoriteDetail(favModel: FavoriteDetailModel)

}