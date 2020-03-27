package com.example.searchmovieproject.retrofit

import com.example.searchmovieproject.pojo.TMDBResponseModelByID
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterfaceById {

    @GET("{inputId}")
    fun getMovieListById(
        @Path("inputId")id : String,
        @Query("api_key")api_key : String
    ): Observable<TMDBResponseModelByID>
}