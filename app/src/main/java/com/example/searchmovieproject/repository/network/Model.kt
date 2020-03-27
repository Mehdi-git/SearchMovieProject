package com.example.searchmovieproject.repository.network

import com.example.searchmovieproject.pojo.TMDBResponseModel
import com.example.searchmovieproject.pojo.TMDBResponseModelByID
import com.example.searchmovieproject.retrofit.RetrofitInterface
import com.example.searchmovieproject.retrofit.RetrofitInterfaceById
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Model ( private val retrofit: Retrofit) {

    private fun getMovieData(): RetrofitInterface {

        return retrofit.create(RetrofitInterface::class.java)
    }

    fun getMovieObservable(apiKey: String, name: String): Observable<TMDBResponseModel> {

        return getMovieData().getMovieList(apiKey, name)
    }





    private fun getMovieDataById(): RetrofitInterfaceById {
       val retrofit =
           Retrofit.Builder()
           .baseUrl("https://api.themoviedb.org/3/movie/")
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .build()

       return retrofit.create(RetrofitInterfaceById::class.java)
    }

       fun getMovieObservableById(id: String, api_key: String): Observable<TMDBResponseModelByID> {

       return getMovieDataById().getMovieListById(id, api_key)
    }
}

