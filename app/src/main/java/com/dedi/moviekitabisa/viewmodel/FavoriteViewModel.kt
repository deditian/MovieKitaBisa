package com.dedi.moviekitabisa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dedi.moviekitabisa.adapter.HomeActivityAdapter
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel

import com.dedi.moviekitabisa.repository.LocalCallback
import org.koin.android.ext.android.inject

class FavoriteViewModel (private val localCallback: LocalCallback): ViewModel(){
    fun getAllFavoriteDetail(): LiveData<PagedList<FavoriteDetailModel>> {
        println("deditian FavoriteViewModel getAllFavoriteDetail ")
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20).build()

        return LivePagedListBuilder(localCallback.getAllFavoriteDetail(), pagedListConfig).build()
    }

}