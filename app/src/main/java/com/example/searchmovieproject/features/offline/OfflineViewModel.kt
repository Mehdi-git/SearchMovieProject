package com.example.searchmovieproject.features.offline

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.searchmovieproject.base.BaseViewModel
import com.example.searchmovieproject.repository.local.MyRoomDB
import com.example.searchmovieproject.repository.local.OfflineData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OfflineViewModel (private  val db: MyRoomDB) : BaseViewModel() {

    private val myDbResponse = MutableLiveData <List<OfflineData>>()
    private val myDbResponseById = MutableLiveData <OfflineData>()




    // Get database information and put it to LiveData
    @SuppressLint("CheckResult")
    fun getFromDB(){
        db.offlineDataDao().getAllList().subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                {
                    myDbResponse.value=it },
                {
                    Log.d("TAG","data did not provided")
                })
    }
    @SuppressLint("CheckResult")
    fun getFromDbById(id:Int){
        db.offlineDataDao().getOfflineListById(id).subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                {
                    myDbResponseById.value=it },
                {
                    Log.d("TAG","data did not provided")
                })
    }



    //LiveData for offline request
    fun getResponseFromDb(): LiveData<List<OfflineData>> = myDbResponse
    fun getResponseFromDbById(): LiveData<OfflineData> = myDbResponseById


}