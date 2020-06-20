package com.dedi.moviekitabisa.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dedi.moviekitabisa.data.entity.FavoriteModel


@Dao
interface FavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favModel: FavoriteModel)

    @Query("SELECT * from favorite_table WHERE statusfav IN (:status) ORDER BY titlefav ASC")
    fun getAllMovie(status: String): DataSource.Factory<Int, FavoriteModel>

    @Query("SELECT * from favorite_table WHERE statusfav IN (:status) ORDER BY titlefav ASC")
    fun getAllTvShow(status : String): DataSource.Factory<Int, FavoriteModel>

    @Query("SELECT * FROM favorite_table WHERE idfav IN (:id)")
    fun getById(id: Int): DataSource.Factory<Int, FavoriteModel>

    @Delete
    fun delete(favModel: FavoriteModel)

}