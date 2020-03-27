package com.example.searchmovieproject.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [OfflineData::class], version = 1)

abstract class MyRoomDB :RoomDatabase() {
    abstract fun offlineDataDao (): MovieDao

}
