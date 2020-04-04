package com.example.searchmovieproject.repository.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OfflineData(

    @PrimaryKey(autoGenerate = true)
    var id: Int?,
   // @ColumnInfo(name ="name")
    var name: String,
    var poster_path: String,
    var budget: Int,
    var genre: String,
    var backdrop_path: String,
    var overview: String,
    var release_date: String,
    var production_companies: String,
    var tagline: String,
    var vote_average : Double


)
