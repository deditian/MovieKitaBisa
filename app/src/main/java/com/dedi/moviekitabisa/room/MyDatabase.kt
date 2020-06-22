package com.dedi.moviekitabisa.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel
import com.dedi.moviekitabisa.data.entity.ResultReview

@Database(entities = [FavoriteDetailModel::class, ResultReview::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun favDao(): FavDao
    abstract fun reviewDao(): ReviewDao
}