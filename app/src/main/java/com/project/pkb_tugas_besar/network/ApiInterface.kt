package com.project.pkb_tugas_besar.network

import com.project.pkb_tugas_besar.data.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(".")
    fun getAllNews(): Call<AllNewsResponse>

    @GET("nasional")
    fun getNationalNews(): Call<NationalNewsResponse>

    @GET("internasional")
    fun getInternationalNews(): Call<InternationalNewsResponse>

    @GET("detail/?{url=}")
    fun getDetailNews(
        @Query("url") url: String
    ): Call<DetailNewsResponse>

    @GET("search/?{q=}")
    fun getSearchNews(
        @Query("q") q: String
    ): Call<SearchNewsResponse>

}