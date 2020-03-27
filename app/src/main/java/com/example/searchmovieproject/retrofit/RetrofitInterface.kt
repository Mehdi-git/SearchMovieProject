package com.example.searchmovieproject.retrofit

import io.reactivex.Observable

import com.example.searchmovieproject.pojo.TMDBResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("movie")
    fun getMovieList(
        @Query("api_key")api_key : String,
        @Query("query")query : String
    ): Observable<TMDBResponseModel>
}