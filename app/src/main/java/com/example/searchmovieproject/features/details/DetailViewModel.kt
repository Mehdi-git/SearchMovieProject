package com.example.searchmovieproject.features.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.searchmovieproject.base.BaseViewModel
import com.example.searchmovieproject.repository.network.Model
import com.example.searchmovieproject.pojo.TMDBResponseModelByID
import com.example.searchmovieproject.repository.local.MyRoomDB
import com.example.searchmovieproject.repository.local.OfflineData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel (private val model : Model, private  val db: MyRoomDB): BaseViewModel() {

    private val disposable= CompositeDisposable()
    private val movieResponseById = MutableLiveData<TMDBResponseModelByID>()
    private val errorResponseById = MutableLiveData<String>()
    private val myTestResponse = MutableLiveData<List<OfflineData>>()


    @SuppressLint("CheckResult")
    fun getFromDB(){
        db.offlineDataDao().getAllList().subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                {
                    myTestResponse.value=it },
                {
                    Log.d("TAG","data did not provided")
                })
    }


    //Add Specifics movie details to database
    fun addToDB(it:TMDBResponseModelByID) {
        GlobalScope.launch {
            db.offlineDataDao().insertList( OfflineData(
                it.id,
                it.original_title,
                it.poster_path,
                it.budget,
                it.genres[0].name,
                it.backdrop_path,
                it.overview,
                it.release_date,
                it.production_companies[0].name,
                it.tagline,
                it.vote_average

            ))
            val data = db.offlineDataDao().getAllList()
            data.forEach {
                println(it.toString())
                Log.d("TAG",it.toString())
            }
        }

    }

    // get movie data with specific id for more details
    fun getMovieListById(id:String,api_key:String){
        disposable.add(model.getMovieObservableById(id,api_key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                movieResponseById.value=it
                Log.d("TAGByID",it.original_title)
            },{
                errorResponseById.value= "Some Error Happend"
               // Log.d("TAGByID", it.message)
            }))

    }
    //LiveData for online request
    fun getMovieResponseById() : LiveData<TMDBResponseModelByID> = movieResponseById


    fun testResponse () : LiveData<List<OfflineData>> = myTestResponse





    //for avoid memory leak
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }


}



