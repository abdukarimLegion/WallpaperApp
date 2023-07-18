package com.vasertoker.wallperapp.retrofit

import com.vasertoker.wallperapp.models.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/photos")
    fun getImage(@Query("query") query: String, @Query("client_id") client_id: String,@Query("per_page") per_page: Int ) : Call<Image>
}