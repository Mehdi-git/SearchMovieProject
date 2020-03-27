package com.example.searchmovieproject.features.offline

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.searchmovieproject.R
import com.example.searchmovieproject.base.extentions.setGlide
import com.example.searchmovieproject.repository.local.OfflineData

import kotlinx.android.synthetic.main.activity_offline_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class OfflineDetail : AppCompatActivity() {
    private val vm: OfflineViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_detail)

        //get id of movie from database view by click on item
        val id = intent.getIntExtra("DbId", 0)

        //request specific movie from database by id
        vm.getFromDbById(id)

        //get response from LiveData
        vm.getResponseFromDbById().observe(this, Observer {
            putData(it)

        })
    }

    @SuppressLint("SetTextI18n")
    private fun putData(it: OfflineData) {
        txtTitleOff.text = it.name
        txtTagOff.text = it.tagline
        txtOverOff.text = it.overview
        txtGenreOff.text = "Genre:    " + it.genre
        txtProductOff.text = "Product: " + it.production_companies
        txtBudgetOff.text = "Budget:  $ " + it.budget.div(1000000) + "M"
        txtYearOff.text = "Year:       " + it.release_date.subSequence(0, 4).toString()
        txtVoteOff.text="Vote Average: " + it.vote_average
        setGlide(it.poster_path,imgForeOff)
        setGlide(it.backdrop_path,imgBackOff)


    }
}
