package com.geta.qr_scan.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private const val BASE_URL: String = "https://pokemon_api/" //@TODO change api url Base

    private val moshi : Moshi by lazy {
        Moshi.Builder().build()
        //GsonBuilder().setLenient().create() // with GSon
    }

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            // .addConverterFactory(GsonConverterFactory.create(gson)) // with GSon
            .build()
    }

    val apiService : ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }

}