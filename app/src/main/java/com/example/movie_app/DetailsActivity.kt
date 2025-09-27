package com.example.movie_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.movie_app.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var b: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val b = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(b.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backG= b.background
        val pstr =b.posterInside
        val title=b.movieName
        val calender=b.date
        val background =intent.getStringExtra("background")
        val poster =intent.getStringExtra("poster")
        val name =intent.getStringExtra("title")
        val date =intent.getStringExtra("date")
        Glide.with(this)
            .load(background)
            .error(R.drawable.baseline_image_24)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(b.background)
        Glide.with(this)
            .load(poster)
            .error(R.drawable.baseline_image_24)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(b.posterInside)
            title.text=name
            calender.text=date
    }
}