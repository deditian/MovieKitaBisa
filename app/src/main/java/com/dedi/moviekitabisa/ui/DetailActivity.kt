package com.dedi.moviekitabisa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dedi.moviekitabisa.R
import com.dedi.moviekitabisa.utils.imageLoad
import com.dedi.moviekitabisa.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {
    private val id_data by lazy {
        intent.getIntExtra("data_id",0)
    }

    private val viewModel: MoviesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        println("deditian Detail  id_data : $id_data")
        observeViewModelIDDetail(id_data)
    }

    private fun observeViewModelIDDetail(id_data: Int) {
        viewModel.getMoviesIdDetail(id_data).observe(this, Observer {data ->
            if (data != null){
                imgdetailPoster.imageLoad(data.poster_path)
                txtdetailTitle.text = data.title
                txtdetailOverview.text = data.overview
                txtdetailReleaseDate.text = data.release_date

            }else{

            }
        })
    }


}
