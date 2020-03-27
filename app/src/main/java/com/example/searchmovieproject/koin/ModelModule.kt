package com.example.searchmovieproject.koin

import com.example.searchmovieproject.repository.network.Model
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val modelModule = module {


   single {
       Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/3/search/")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()}

   single{ Model(get()) }


}










