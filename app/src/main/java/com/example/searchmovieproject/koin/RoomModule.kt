package com.example.searchmovieproject.koin

import android.content.Context
import androidx.room.Room
import com.example.searchmovieproject.repository.local.MyRoomDB
import org.koin.dsl.module

val RoomModule = module {
    single {
        Room.databaseBuilder(get(), MyRoomDB::class.java, "OfflineData").build()

    }

    single { get<MyRoomDB>().offlineDataDao() }

}