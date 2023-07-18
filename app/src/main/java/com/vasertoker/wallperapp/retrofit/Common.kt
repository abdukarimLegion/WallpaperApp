package com.vasertoker.retrofit2.retrofit

import com.vasertoker.wallperapp.retrofit.RetrofitClient
import com.vasertoker.wallperapp.retrofit.RetrofitService

object Common {
    const val BASE_URL = "https://api.unsplash.com/"

  fun retrofitService() : RetrofitService {
    return  RetrofitClient.getRetrofit(BASE_URL).create(RetrofitService::class.java)

  }
}