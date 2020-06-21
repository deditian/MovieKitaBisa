package com.dedi.moviekitabisa.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dedi.moviekitabisa.R

import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.entity.Movie
import com.dedi.moviekitabisa.ui.DetailActivity
import com.dedi.moviekitabisa.utils.imageLoad
import kotlinx.android.synthetic.main.item_content.view.*

class HomeActivityAdapter : RecyclerView.Adapter<HomeActivityAdapter.MoviesViewHolder>() {
    private val mCourses = ArrayList<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false))
    }

    override fun getItemCount(): Int {
        return mCourses.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(mCourses[position])
    }

    fun setListMovies(listCourses: List<Movie>) {
        this.mCourses.clear()
        this.mCourses.addAll(listCourses)
    }

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(results: Movie) = itemView.run {
            txtTitle.text = results.original_title
            txtReleasedate.text = results.release_date
            println("deditian url image : ${results.poster_path}")
            img_poster.imageLoad(results.poster_path)
            txtDesctription.text = results.overview
            cv_item_course.setOnClickListener {
                val mIntent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("data_id",
                        DetailRespone(results.id,results.poster_path,results.adult,results.overview,
                            results.release_date,results.title,results.original_title,results.original_language,results.backdrop_path,
                            results.popularity,results.vote_count,results.video,results.vote_average))
                }
                context.startActivity(mIntent)
            }
        }
    }

}