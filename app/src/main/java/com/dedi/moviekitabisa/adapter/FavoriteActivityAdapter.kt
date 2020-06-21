package com.dedi.moviekitabisa.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dedi.moviekitabisa.R
import com.dedi.moviekitabisa.data.DetailRespone
import com.dedi.moviekitabisa.data.entity.FavoriteDetailModel
import com.dedi.moviekitabisa.data.entity.Movie
import com.dedi.moviekitabisa.ui.DetailActivity
import com.dedi.moviekitabisa.utils.imageLoad
import kotlinx.android.synthetic.main.item_content.view.*


class FavoriteActivityAdapter : PagedListAdapter<FavoriteDetailModel, FavoriteActivityAdapter.FavMoviesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteDetailModel>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(oldFav: FavoriteDetailModel, newFav: FavoriteDetailModel): Boolean {
                return oldFav.title == newFav.title
            }

            override fun areContentsTheSame(oldFav: FavoriteDetailModel, newFav: FavoriteDetailModel): Boolean {
                return oldFav == newFav
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMoviesViewHolder {
        return FavMoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false))
    }


    override fun onBindViewHolder(holder: FavMoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class FavMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(results: FavoriteDetailModel?) = itemView.run {
            txtTitle.text = results?.original_title
            txtReleasedate.text = results?.release_date
            println("deditian url image FavoriteActivityAdapter : ${results?.poster_path}")
            img_poster.imageLoad(results?.poster_path.toString())
            txtDesctription.text = results?.overview
            cv_item_course.setOnClickListener {
                val mIntent = Intent(context, DetailActivity::class.java).apply {
                    if (results != null) {
                        putExtra("data_id",
                            DetailRespone(results.id,results.poster_path,results.adult,results.overview,
                                results.release_date,results.title,results.original_title,results.original_language,results.backdrop_path,
                                results.popularity,results.vote_count,results.video,results.vote_average)
                        )
                    }
                }
                context.startActivity(mIntent)
            }
        }
    }

}