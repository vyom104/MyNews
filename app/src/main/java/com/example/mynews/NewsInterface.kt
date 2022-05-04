package com.example.mynews

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=7862c9f82a534603baebe80b834f6bda
const val base_url="https://newsapi.org/"
const val api_key="7862c9f82a534603baebe80b834f6bda"
interface ApiInterface {
    @GET("v2/top-headlines?apiKey=$api_key")
    fun getHeadlines( @Query("country")country : String , @Query("page")page : Int): Call<News>
    //https://newsapi.org/v2/top-headlines?apiKey=7862c9f82a534603baebe80b834f6bda&country=in&page=1
}
object NewsInterface{
    val newsInstance:ApiInterface
    init {
        val retrofit=Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build()
        newsInstance=retrofit.create(ApiInterface::class.java)

    }
}