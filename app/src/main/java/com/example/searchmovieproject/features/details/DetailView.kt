package com.example.searchmovieproject.features.details

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.database.Observable
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.searchmovieproject.R
import com.example.searchmovieproject.base.BaseActivity
import com.example.searchmovieproject.base.extentions.setGlide
import com.example.searchmovieproject.base.extentions.setPicasso
import com.example.searchmovieproject.base.extentions.showToast
import com.example.searchmovieproject.features.offline.OfflineView
import com.example.searchmovieproject.pojo.TMDBResponseModelByID
import com.example.searchmovieproject.repository.local.MyRoomDB
import com.example.searchmovieproject.repository.local.OfflineData
import com.example.searchmovieproject.utils.APIKEY
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.offline_recycler_item.view.*
import org.koin.android.viewmodel.ext.android.viewModel



class DetailView : BaseActivity() {
    private val viewModel: DetailViewModel by viewModel()

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //get id number of movie which user has clicked on it
        val id = intent.getStringExtra("id")

        //send movie id to viewModel to get result
        if( id ==null){
            Toast.makeText(this,"Sorry could not find the movie!",Toast.LENGTH_SHORT).show()
        }
        else{
            viewModel.getMovieListById(id.toString(), APIKEY)

        }

      // TODO important
          viewModel.getFromDB()

        //get result from viewModel by movie id
        viewModel.getMovieResponseById().observe(this, Observer {
            //put data to UI
            putData(it)

            val data = it
            btnSave.setOnClickListener{
                //send data for store in Database
                sendDataForDb(data)
                showToast("The Movie Added To Favorite")

            }


            //download picture of specific movie and put into the view
            setGlide(it.backdrop_path,imgBack)
            setGlide(it.poster_path,imgFore )


            //make UI Animated
//            val aniFade = AnimationUtils.loadAnimation(applicationContext,
//                R.anim.fade_in)
//                imgBack.startAnimation(aniFade)
//                imgFore.startAnimation(aniFade)
//                txtOver.startAnimation(aniFade)
//                txtTitle.startAnimation(aniFade)
//                txtTag.startAnimation(aniFade)
//                txtYear.startAnimation(aniFade)
//                txtProduct.startAnimation(aniFade)
//                txtBudget.startAnimation(aniFade)
//                txtGenre.startAnimation(aniFade)
//                txtOver.movementMethod = ScrollingMovementMethod()

        })


    }

   private fun sendDataForDb (it:TMDBResponseModelByID){
        viewModel.addToDB(it)
    }
   @SuppressLint("SetTextI18n")
   private fun putData(it: TMDBResponseModelByID) {
        txtTitle.text = it.original_title
        txtTag.text = it.tagline
        txtOver.text = it.overview
        txtGenre.text = "Genre:    " + it.genres[0].name
        txtProduct.text = "Product: " + it.production_companies[0].name
        txtBudget.text = "Budget:  $ " + it.budget.div(1000000) + "M"
        txtYear.text = "Year:       " + it.release_date.subSequence(0, 4).toString()
        txtVote.text="Vote Average: " + it.vote_average

   }
}


