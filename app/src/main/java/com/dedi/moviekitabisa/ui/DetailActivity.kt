package com.dedi.moviekitabisa.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedi.moviekitabisa.R
import com.dedi.moviekitabisa.adapter.DetailActivityAdapter
import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel
import com.dedi.moviekitabisa.data.entity.FavoriteReviewModel

import com.dedi.moviekitabisa.data.entity.ResultReview
import com.dedi.moviekitabisa.utils.imageLoad
import com.dedi.moviekitabisa.viewmodel.DetailViewModel
import com.dedi.moviekitabisa.viewmodel.FavoriteViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import es.dmoral.toasty.Toasty

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_favorite.*

import kotlinx.android.synthetic.main.bottom_sheet_detail_review.*
import org.koin.android.ext.android.inject

class DetailActivity : AppCompatActivity() {

    private val mCourses = ArrayList<ResultReview>()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    var detailActivityAdapter: DetailActivityAdapter? = null
    private var pilih : Boolean = false
    private val id_data by lazy {
        intent.getParcelableExtra<DetailRespone>("data_id")
    }

    private val viewModel: DetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        println("deditian Detail  id_data : ${id_data.id}")
        bottomSheetDetail()

        setFavorite(pilih)
        detailActivityAdapter = DetailActivityAdapter()
        rv_history_review?.layoutManager = LinearLayoutManager(this)
        rv_history_review?.setHasFixedSize(true)
        rv_history_review?.adapter = detailActivityAdapter
        observeViewModelReviewerAndDetail(id_data.id)
        viewModel.getAllFavoriteDetailID(id_data.id).observe(this, Observer {data ->
            println("deditian getAllFavoriteDetailID DetailActivity $data")
            pilih = if (data.isNotEmpty()) {
                println("deditian getAllFavoriteDetailID DetailActivity $data pilih : $pilih")
                setFavorite(true)
                false
            }else{
                println("deditian getAllFavoriteDetailID DetailActivity $data pilih else : $pilih")
                setFavorite(false)
                true
            }
        })


        id_data.let {
            imgdetailPoster.imageLoad(it.poster_path)
            txtdetailTitle.text = it.title
            txtdetailOverview.text = it.overview
            txtdetailReleaseDate.text = it.release_date
        }

        btnDetailFavorite.setOnClickListener {
            actionClick()
        }

    }

    private fun actionClick() {
        if (pilih){
            viewModel.insertDetail(
                FavoriteDetailModel(
                    id_data.id,id_data.poster_path,id_data.adult,id_data.overview,
                    id_data.release_date,id_data.title,id_data.original_title,id_data.original_language,id_data.backdrop_path,
                    id_data.popularity,id_data.vote_count,id_data.video,id_data.vote_average))
            setFavorite(true)
            pilih = false
            Toasty.success(this, "Save", Toasty.LENGTH_SHORT).show()
        }else{
            viewModel.deleteFavoriteDetail(
                FavoriteDetailModel(
                    id_data.id,id_data.poster_path,id_data.adult,id_data.overview,
                    id_data.release_date,id_data.title,id_data.original_title,id_data.original_language,id_data.backdrop_path,
                    id_data.popularity,id_data.vote_count,id_data.video,id_data.vote_average))
            setFavorite(false)
            pilih = true
            Toasty.success(this, "Delete", Toasty.LENGTH_SHORT).show()
        }
    }


    private fun observeViewModelReviewerAndDetail(id_data: Int) {
        viewModel.getMoviesIdDetail(id_data).observe(this, Observer {data ->
//            if (data.id != null) {
//                setFavorite(true)
//                pilih =false
//            }else{
//                setFavorite(false)
//                pilih = true
//            }
//            if (data != null){
//                viewModel.insertDetail(
//                    FavoriteDetailModel(
//                        data.id,data.poster_path,data.adult,data.overview,
//                        data.release_date,data.title,data.original_title,data.original_language,data.backdrop_path,
//                        data.popularity,data.vote_count,data.video,data.vote_average))
//                imgdetailPoster.imageLoad(data.poster_path)
//                txtdetailTitle.text = data.title
//                txtdetailOverview.text = data.overview
//                txtdetailReleaseDate.text = data.release_date
//            }
//            else{
//                viewModel.getAllFavoriteDetailID(id_data).observe(this, Observer {dataFavorite ->
//                    println("deditian observeViewModelFavoriteDetail $dataFavorite")
//                    imgdetailPoster.imageLoad(dataFavorite[0]?.poster_path.toString())
//                    txtdetailTitle.text = dataFavorite[0]?.title
//                    txtdetailOverview.text = dataFavorite[0]?.overview
//                    txtdetailReleaseDate.text = dataFavorite[0]?.release_date
//
//
//
//                })
//            }
        })

        println("deditian observeViewModelReviewer $id_data")
        viewModel.getMoviesIdReviewDetail(id_data).observe(this, Observer {data ->
            if (data != null){
                txtReviewer.text = "Jumlah Reviewer : "+data.total_results.toString()
                detailActivityAdapter?.setListMovies(data.results)
                detailActivityAdapter?.notifyDataSetChanged()
            }else{
//                viewModel.insertReview(FavoriteReviewModel(data.id,data.page,data.total_pages,data.total_results))
            }

        })

        viewModel.getMoviesIdReviewResultDetail(id_data).observe(this, Observer {data ->
            if (data != null){
//                viewModel.insertReviewResult(FavoriteReviewModelResult(data.id,data.author,data.content,data.url))
            }
        })

    }

    private fun bottomSheetDetail(){
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

    private fun setFavorite(boolean: Boolean) {
        if (boolean) {
            btnDetailFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_black_24dp))
        } else {
            btnDetailFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_unfavorite_border_black_24dp))
        }
    }



}
