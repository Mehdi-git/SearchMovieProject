package com.example.searchmovieproject.features.offline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovieproject.R
import com.example.searchmovieproject.repository.local.OfflineData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.offline_recycler_item.view.*

class OffAdapter ( private val list : List <OfflineData>, private val onClickListener: (Int?) -> Unit): RecyclerView.Adapter<OffAdapter.OfflineHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfflineHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.offline_recycler_item, parent, false)
        return OffAdapter.OfflineHolder(v, onClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OfflineHolder, position: Int) {
        holder.onBind(list[position])
        //doPicasso(list[position].poster_path ,holder.itemView.imgOfflinePoster)
    }

    class OfflineHolder(private val view: View, val onClickListener: (Int?) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun onBind(movieList: OfflineData) {
            view.txtOfflineName.text = movieList.name
            view.txtOfflineYear.text = movieList.release_date.subSequence(0, 4).toString()
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + movieList.poster_path)
                .into(view.imgOfflinePoster)
            view.setOnClickListener(View.OnClickListener {
                onClickListener(movieList.id)
            })

        }

    }
}