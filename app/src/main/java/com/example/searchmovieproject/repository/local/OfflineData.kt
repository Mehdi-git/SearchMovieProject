package com.example.searchmovieproject.repository.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OfflineData(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name ="name")
    val name: String,
    val poster_path: String,
    val budget: Int,
    val genre: String,
    val backdrop_path: String,
    val overview: String,
    val release_date: String,
    val production_companies: String,
    val tagline: String,
    val vote_average : Double


)
