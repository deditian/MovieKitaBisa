package com.dedi.moviekitabisa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dedi.moviekitabisa.R
import com.dedi.moviekitabisa.data.entity.ResultReview
import kotlinx.android.synthetic.main.item_content_review.view.*


class DetailActivityAdapter : RecyclerView.Adapter<DetailActivityAdapter.ReviewViewHolder>() {
    private val mCourses = ArrayList<ResultReview>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder = ReviewViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_content_review, parent,false)
    )

    override fun getItemCount(): Int {
        return mCourses.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(mCourses[position])
    }

    fun setListMovies(listCourses: List<ResultReview>) {
        this.mCourses.clear()
        this.mCourses.addAll(listCourses)
    }

    inner class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(results: ResultReview) = itemView.run {
            txtReviewAuthor.text = results.author
            txtReviewURL.text = results.url
            txtReviewContent.text = results.content
        }
    }
}