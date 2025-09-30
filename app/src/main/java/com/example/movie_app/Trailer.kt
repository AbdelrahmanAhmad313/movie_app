package com.example.movie_app

import android.provider.MediaStore.Video
import com.google.gson.annotations.SerializedName

data class Trailer(
    val results: ArrayList<Links>
)


data class Links(
    @SerializedName("key")
    val key: String
)