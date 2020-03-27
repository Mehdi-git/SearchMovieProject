package com.example.searchmovieproject.features.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchmovieproject.R
import com.example.searchmovieproject.pojo.Result
import kotlinx.android.synthetic.main.recycler_item.view.*

class SearchAdapter(private val list : List<Result> , private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.SearchRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return SearchRecyclerViewHolder(v,clickListener)
    }

    override fun getItemCount(): Int {

       return  list.size
    }

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
       holder.onBind(list[position].original_title,
                     list[position].id.toString(),
                     list[position].release_date.subSequence(0, 4).toString()

       )

    }
    class SearchRecyclerViewHolder(private val item: View, val clickListener: (String) -> Unit) : RecyclerView.ViewHolder(item) {

        fun onBind (movieName : String, movieId : String , movieYear:String ){
            item.txtMovieName.text = movieName
            item.txtId.text=movieId.toString()
            item.txtMovieYear.text= movieYear

             item.setOnClickListener(View.OnClickListener {
                 clickListener(movieId)
             })
        }

        }

    }

