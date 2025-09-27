package com.example.movie_app

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.movie_app.databinding.ActivityDetailsBinding
import com.example.movie_app.databinding.MoviesListItemBinding

class CinemaAdapter(val a: Activity, val movies: ArrayList<Results>) :
    Adapter<CinemaAdapter.CinemaViewHolder>() {
    class CinemaViewHolder(val b: MoviesListItemBinding) : ViewHolder(b.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val bv = MoviesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        val bd =ActivityDetailsBinding.inflate(LayoutInflater.from(parent.context),parent,true)
        return CinemaViewHolder(bv)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        val imageLink = "https://image.tmdb.org/t/p/w500${movies[position].poster}"
        Glide.with(holder.b.posterImage.context)
            .load(imageLink)
            .error(R.drawable.baseline_image_24)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(holder.b.posterImage)
        Log.d("sure","id=${movies[0].id}")

        holder.b.posterImage.setOnClickListener {
            val i=Intent(holder.itemView.context,DetailsActivity::class.java)
            i.putExtra("background","https://image.tmdb.org/t/p/w500${movies[position].background}")
            i.putExtra("poster",imageLink)
            i.putExtra("title",movies[position].title)
            i.putExtra("date",movies[position].date)
            holder.itemView.context.startActivity(i)
        }


    }
}