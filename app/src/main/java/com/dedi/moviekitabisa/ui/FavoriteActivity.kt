package com.dedi.moviekitabisa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedi.moviekitabisa.R
import com.dedi.moviekitabisa.adapter.FavoriteActivityAdapter
import com.dedi.moviekitabisa.adapter.HomeActivityAdapter
import com.dedi.moviekitabisa.viewmodel.FavoriteViewModel
import com.dedi.moviekitabisa.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject

class FavoriteActivity : AppCompatActivity() {
    private var favoriteActivityAdapter: FavoriteActivityAdapter? = null
    private val viewModel: FavoriteViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        favoriteActivityAdapter = FavoriteActivityAdapter()
        rv_fav_detail?.layoutManager = LinearLayoutManager(this)
        rv_fav_detail?.setHasFixedSize(true)
        rv_fav_detail?.adapter = favoriteActivityAdapter
        observeViewModelFavoriteDetail()
    }

    private fun observeViewModelFavoriteDetail() {
        viewModel.getAllFavoriteDetail().observe(this, Observer {data ->
            if (data != null){
                txt_empty_favorite.visibility = View.GONE
                favoriteActivityAdapter?.submitList(data)
                favoriteActivityAdapter?.notifyDataSetChanged()
            }else{
                txt_empty_favorite.visibility = View.VISIBLE
            }

        })
    }
}
