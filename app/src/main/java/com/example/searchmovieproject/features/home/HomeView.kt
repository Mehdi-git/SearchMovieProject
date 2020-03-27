package com.example.searchmovieproject.features.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import com.example.searchmovieproject.R
import com.example.searchmovieproject.features.details.DetailView
import com.example.searchmovieproject.features.offline.OfflineView
import com.example.searchmovieproject.features.search.SearchView
import kotlinx.android.synthetic.main.activity_home_view.*

class HomeView : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_view)

        btnSearch.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SearchView::class.java)
            startActivity(intent)
        })

        btnOffline.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, OfflineView::class.java)
            startActivity(intent)

        })
        btnAboutUs.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AboutUs::class.java)
            startActivity(intent)
        })




    }




    }

