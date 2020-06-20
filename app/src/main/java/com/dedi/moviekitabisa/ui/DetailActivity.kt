package com.dedi.moviekitabisa.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedi.moviekitabisa.R
import com.dedi.moviekitabisa.adapter.DetailActivityAdapter
import com.dedi.moviekitabisa.utils.imageLoad
import com.dedi.moviekitabisa.viewmodel.MoviesViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_detail.*

import kotlinx.android.synthetic.main.bottom_sheet_detail_review.*

import kotlinx.android.synthetic.main.item_content_review.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    var detailActivityAdapter: DetailActivityAdapter? = null
    private val id_data by lazy {
        intent.getIntExtra("data_id",0)
    }

    private val viewModel: MoviesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        println("deditian Detail  id_data : $id_data")
        observeViewModelIDDetail(id_data)
        bottomSheetDetail(id_data)
        observeViewModelReviewer(id_data)

        detailActivityAdapter = DetailActivityAdapter()
        rv_history_review?.layoutManager = LinearLayoutManager(this)
        rv_history_review?.setHasFixedSize(true)
        rv_history_review?.adapter = detailActivityAdapter

    }

    private fun observeViewModelReviewer(id_data: Int) {
        println("deditian observeViewModelReviewer $id_data")
        viewModel.getMoviesIdReviewDetail(id_data).observe(this, Observer {data ->
            if (data != null){
//                txtReviewer.text = data.total_results.toString()
            println("deditian getMoviesIdReviewDetail ${data.id}")
                txtReviewer.text = "Jumlah Reviewer : "+data.total_results.toString()
            }else{

            }
            detailActivityAdapter?.setListMovies(data.results)
            detailActivityAdapter?.notifyDataSetChanged()


        })
    }

    private fun observeViewModelIDDetail(id_data: Int) {
        viewModel.getMoviesIdDetail(id_data).observe(this, Observer {data ->
            if (data != null){
                imgdetailPoster.imageLoad(data.poster_path)
                txtdetailTitle.text = data.title
                txtdetailOverview.text = data.overview
                txtdetailReleaseDate.text = data.release_date

            }
        })
    }

    private fun bottomSheetDetail(id_data: Int){
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_detail)
        btnReviewer.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        }

        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
//                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                }
            }

            override fun onSlide(p0: View, p1: Float) {

            }
        })
    }




}
