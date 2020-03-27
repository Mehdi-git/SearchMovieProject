package com.example.searchmovieproject.features.offline

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchmovieproject.R
import com.example.searchmovieproject.base.BaseActivity
import com.example.searchmovieproject.features.details.DetailView
import kotlinx.android.synthetic.main.activity_offline_view.*
import org.koin.android.viewmodel.ext.android.viewModel


class OfflineView : BaseActivity() {
   private val vm : OfflineViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_view)

        val onClickListener: (Int?) -> Unit = {
            val intent = Intent(this, OfflineDetail::class.java)
            intent.putExtra("DbId", it)
            startActivity(intent)
        }
        //get data from data base and made LiveData
         vm.getFromDB()

        //Observe on LiveData and put data to adapter
        vm.getResponseFromDb().observe(this, Observer {
           val adapter = OffAdapter(it,onClickListener)
            recyclerOffline.adapter=adapter

        })





    }


}
