package com.example.searchmovieproject.features.search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.searchmovieproject.R
import com.example.searchmovieproject.base.BaseActivity
import com.example.searchmovieproject.features.details.DetailView
import com.example.searchmovieproject.pojo.TMDBResponseModel
import com.example.searchmovieproject.repository.local.MyRoomDB
import com.example.searchmovieproject.repository.local.OfflineData
import com.example.searchmovieproject.utils.APIKEY
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.activity_search_view.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchView : BaseActivity() {

    private val vm: SearchViewModel by viewModel()
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)

        //sending movie id to detail feature
        val clickListener: (String) -> Unit = {
            val intent = Intent(this, DetailView::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        //show result by RxBinding
        edtName.textChanges()
            .filter{ it.length>2 }
            .subscribe({
                val name = it.toString()
                vm.getMovieList(APIKEY, name)
                vm.getMovieResponse().observe(this, Observer {
                    val adapter = SearchAdapter(it.results, clickListener)
                    if (it.total_results > 0) { recycler.adapter = adapter }
                })
                }, {
                Log.d("TAG", it.message)
                })

        //show result by search key
        btnSearch.setOnClickListener {
            val name = edtName.text.toString()
            vm.getMovieList(APIKEY, name)
            vm.getMovieResponse().observe(this, Observer {
                val adapter = SearchAdapter(it.results, clickListener)
                if (it.total_results > 0) {
                    recycler.adapter = adapter
                } else
                    Toast.makeText(this, "Movie Could Not Be Found", Toast.LENGTH_SHORT).show()


            })
        }


    }
}


