package com.example.movie_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaCallable {
    @GET("movie/popular?language=en-US&page=1&api_key=d997190299b8c60ad08ef02b0dc4c804")
    fun getMovies():Call<Cinema>
    @GET("movie/{id}/videos?api_key=d997190299b8c60ad08ef02b0dc4c804")
    fun getVid(@Path("id") id:String):Call<Trailer>
}