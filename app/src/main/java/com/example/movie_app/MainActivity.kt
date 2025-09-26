package com.example.movie_app


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.movie_app.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var b:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b =ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // apikey= d997190299b8c60ad08ef02b0dc4c804
        //base url? =https://api.themoviedb.org/3/'what i want' &api_key=d997190299b8c60ad08ef02b0dc4c804
        // image =https://image.tmdb.org/t/p/w500 + poster

        loadMovies()

    }

    private fun loadMovies(){
        val retrofit=Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val c =retrofit.create(CinemaCallable::class.java)
            c.getMovies().enqueue(object :Callback<Cinema>{
                override fun onResponse(call: Call<Cinema>, response: Response<Cinema>) {
                    val cinema =response.body()
                    val movies=cinema?.results!!
                    showMovies(movies)
                    b.progress.isVisible=false


                }

                override fun onFailure(call: Call<Cinema>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                }

            })


    }
    private fun showMovies(movies: ArrayList<Results>){
        val adapter =CinemaAdapter(this, movies)
        b.movieList.adapter=adapter
    }

}