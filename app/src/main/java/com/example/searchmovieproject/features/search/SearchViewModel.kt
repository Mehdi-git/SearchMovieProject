package com.example.searchmovieproject.features.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.searchmovieproject.base.BaseViewModel
import com.example.searchmovieproject.pojo.TMDBResponseModel
import com.example.searchmovieproject.repository.network.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SearchViewModel (private val model : Model) : BaseViewModel() {

    private val disposable=CompositeDisposable()
    private val movieResponse = MutableLiveData<TMDBResponseModel>()
    private val errorResponse = MutableLiveData<String>()


    //get result from server by keyword(Movie name)
    fun getMovieList(apiKey:String,name:String){
        disposable.add(model.getMovieObservable(apiKey ,name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                movieResponse.value=it
            },{
                errorResponse.value= "Error happened during subscribe to model"
                Log.d("TAG", it.message)
            }))

    }
    //Send data to LiveData
    fun getMovieResponse() : LiveData<TMDBResponseModel> = movieResponse

    //Send error to LiveData
    fun getErrorResponse() : LiveData<String> = errorResponse


    // closing disposable to Avoid Memory Leak
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }


    }








