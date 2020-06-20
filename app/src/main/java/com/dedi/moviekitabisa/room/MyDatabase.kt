package com.dedi.moviekitabisa.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dedi.moviekitabisa.data.entity.FavoriteModel

@Database(entities = [FavoriteModel::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun favDao(): FavDao
}