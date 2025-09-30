package com.example.movie_app

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movie_app.databinding.ActivityDetailsBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {
    private lateinit var b: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(b.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        b.back.setOnClickListener {

            finish()
        }
        // trailer=https://www.youtube.com/watch?v= +key
        val title = b.movieName
        val calender = b.date
        val background = intent.getStringExtra("background")
        val poster = intent.getStringExtra("poster")
        val name = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val id = intent.getStringExtra("movieId")
//        val trailer = intent.getStringExtra("trailer")
        Glide.with(this)
            .load(background)
            .error(R.drawable.baseline_image_24)
            .into(b.background)
        Glide.with(this)
            .load(poster)
            .error(R.drawable.baseline_image_24)
            .into(b.posterInside)
        title.text = name
        calender.text = date
        b.trailerHolder.setOnClickListener {
            getTrailer(id!!)
        }


    }


    private fun getTrailer(id: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val c = retrofit.create(CinemaCallable::class.java)
        c.getVid(id = id).enqueue(object : Callback<Trailer> {
            override fun onResponse(call: Call<Trailer>, response: Response<Trailer>) {
                val trailer = response.body()
                val video = trailer?.results!!
                showTrailer(video)

            }

            override fun onFailure(call: Call<Trailer>, t: Throwable) {


            }

        })
    }

    private fun showTrailer(video: ArrayList<Links>) {
        val link = "https://www.youtube.com/watch?v=${video[0].key}".toUri()
        val i = Intent(Intent.ACTION_VIEW, link)
        startActivity(i)

    }

}