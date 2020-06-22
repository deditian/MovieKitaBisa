package com.dedi.moviekitabisa.di

import androidx.room.Room
import com.dedi.moviekitabisa.repository.ApiCallback
import com.dedi.moviekitabisa.repository.ApiRepository
import com.dedi.moviekitabisa.repository.LocalCallback
import com.dedi.moviekitabisa.repository.LocalRepository
import com.dedi.moviekitabisa.room.MyDatabase
import com.dedi.moviekitabisa.viewmodel.DetailViewModel
import com.dedi.moviekitabisa.viewmodel.FavoriteViewModel
import com.dedi.moviekitabisa.viewmodel.MoviesViewModel

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext


object DependencyModules {

    val appModules = applicationContext {

        bean { LocalRepository(get(),get()) as LocalCallback }
        bean { ApiRepository() as ApiCallback }

        factory { DetailViewModel(get(),get())}
        factory { MoviesViewModel(get()) }
        factory { FavoriteViewModel(get()) }

        bean { get<MyDatabase>().favDao() }
        bean { get<MyDatabase>().reviewDao() }

        bean {
            Room.databaseBuilder(androidApplication(), MyDatabase::class.java, "Favorites-db").allowMainThreadQueries()
                .build()
        }
    }
}