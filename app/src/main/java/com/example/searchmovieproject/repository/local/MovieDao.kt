package com.example.searchmovieproject.repository.local

import android.database.Observable
import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(OfflineData : OfflineData)

    @Update
    fun updateList(OfflineData: OfflineData)

    @Delete
    fun removeList(OfflineData: OfflineData)

    @Query("SELECT * FROM OfflineData")
    fun getAllList(): io.reactivex.Observable<List<OfflineData>>

    @Query("SELECT * FROM OfflineData WHERE id LIKE :myId")
    fun getOfflineListById(myId:Int):io.reactivex.Observable<OfflineData>

}