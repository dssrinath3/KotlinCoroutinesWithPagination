package com.example.kotlincoroutinespagination.pagingdetails.adapter

import android.util.Log
import android.util.Log.VERBOSE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincoroutinespagination.R
import com.example.kotlincoroutinespagination.pagingdetails.glide.GlideApp
import com.example.kotlincoroutinespagination.pagingdetails.model.Data
import com.example.kotlincoroutinespagination.pagingdetails.model.Movie
import kotlinx.android.synthetic.main.item_list.view.*
import android.util.Log.e as e1

class MainListAdapter :PagingDataAdapter<Movie, MainListAdapter.PagingViewHolder>(DataDifferntiator){

    class PagingViewHolder(view :View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        )
    }


    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
          holder.itemView.textViewName.text = getItem(position)?.title
          holder.itemView.textViewEmail.text = getItem(position)?.overview

        holder.itemView.imageView2?.let {

           /* var img:String = "https://image.tmdb.org/t/p/w500${getItem(position)?.backdrop_path}"
            e1(img)*/
            GlideApp.with(it).load("https://image.tmdb.org/t/p/w500${getItem(position)?.backdrop_path}")
                .placeholder(R.drawable.ic_placeholder_img)
                .error(R.drawable.ic_error_img)
                .into( holder.itemView.imageView2!!)
        }
    }

    private fun e1(img: String) {
        Log.e("image_path"," : ${img}")

    }


    object DataDifferntiator : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }


}


