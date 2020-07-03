package com.dedi.moviekitabisa.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedi.moviekitabisa.R
import com.dedi.moviekitabisa.adapter.HomeActivityAdapter
import com.dedi.moviekitabisa.viewmodel.MoviesViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject
import uz.jamshid.library.progress_bar.LineProgressBar


class HomeActivity : AppCompatActivity() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private var homeActivityAdapter: HomeActivityAdapter? = null
    private val viewModel:MoviesViewModel by inject()
    var swipe = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomSheet()
        homeActivityAdapter = HomeActivityAdapter()
        rv_history?.layoutManager = LinearLayoutManager(this)
        rv_history?.setHasFixedSize(true)
        rv_history?.adapter = homeActivityAdapter
        observeViewModelPopular()

    refreshLayout.setRefreshListener{
        if (swipe == 0){
            observeViewModelPopular()
        }else if (swipe == 1){
            observeViewModelTopRated()
        }else{
            observeViewModelNowPlaying()
        }

        refreshLayout.setRefreshing(false)
        Toasty.success(this,"Refresh", Toasty.LENGTH_SHORT).show()
    }

        refreshLayout.setCustomBar(LineProgressBar(this))




    }

    private fun bottomSheet(){
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        btnCategory.setOnClickListener {
            if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                btnPopular.setOnClickListener {
                    observeViewModelPopular()
                    Toasty.success(this,"Popular", Toasty.LENGTH_SHORT).show()
                }

                btnNowPlaying.setOnClickListener {
                    observeViewModelNowPlaying()
                    Toasty.success(this,"Now Playing", Toasty.LENGTH_SHORT).show()
                }

                btnTopRated.setOnClickListener {
                    observeViewModelTopRated()
                    Toasty.success(this,"Top Rated", Toasty.LENGTH_SHORT).show()
                }


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

    private fun observeViewModelTopRated() {
        swipe = 1
        viewModel.getMoviesTopRated().observe(this, Observer {data ->
            if (data != null){
                txt_empty.visibility = View.GONE
                homeActivityAdapter?.setListMovies(data.results)
                homeActivityAdapter?.notifyDataSetChanged()
            }else{
                txt_empty.visibility = View.VISIBLE
            }


        })
    }

    private fun observeViewModelNowPlaying() {
        swipe = 2
        viewModel.getMoviesNowPlaying().observe(this, Observer {data ->
            if (data != null){
                txt_empty.visibility = View.GONE
                homeActivityAdapter?.setListMovies(data.results)
                homeActivityAdapter?.notifyDataSetChanged()
            }else{
                txt_empty.visibility = View.VISIBLE
            }


        })
    }


    private  fun observeViewModelPopular() {
        swipe = 0
            viewModel.getMoviesPopular().observe(this@HomeActivity, Observer { data ->
                println("deditian dataHME getMoviesPopular ${data}")
                if (data != null) {
                    txt_empty.visibility = View.GONE
                    homeActivityAdapter?.setListMovies(data.results)
                    homeActivityAdapter?.notifyDataSetChanged()
                } else {
                    txt_empty.visibility = View.VISIBLE
                }

            })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menufavorite, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
