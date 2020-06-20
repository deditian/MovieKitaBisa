package com.dedi.moviekitabisa.di

import androidx.room.Room
import com.dedi.moviekitabisa.repository.ApiCallback
import com.dedi.moviekitabisa.repository.ApiRepository
import com.dedi.moviekitabisa.viewmodel.MoviesViewModel

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext


object DependencyModules {

    val appModules = applicationContext {

//        bean { LocalRepository(get()) as LocalCallback }
        bean { ApiRepository() as ApiCallback }

//        factory { DetailViewModel(get()) }

        factory { MoviesViewModel(get()) }

//        factory { TvShowViewModel(get()) }
//
//        factory { FavoriteMovieViewModel(get()) }
//        factory { FavoriteTvShowViewModel(get()) }

//        bean { get<MyDatabase>().favDao() }

//        bean {
//            Room.databaseBuilder(androidApplication(), MyDatabase::class.java, "Favorites-db").allowMainThreadQueries()
//                .build()
//        }
    }
}