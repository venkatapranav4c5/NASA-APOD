package com.pranavkonduru.nasaapod.ui.nasanewslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pranavkonduru.nasaapod.R
import com.pranavkonduru.nasaapod.data.models.NasaNewsItem
import com.pranavkonduru.nasaapod.data.models.listeners.NasaNewsItemClickListener
import com.pranavkonduru.nasaapod.ui.nasanewslist.adapters.viewholder.NasaNewsListViewHolder

class NasaNewsListAdapter(
private val nasaNewsList: List<NasaNewsItem>,
private val nasaNewsItemClickListener: NasaNewsItemClickListener
) : RecyclerView.Adapter<NasaNewsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaNewsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NasaNewsListViewHolder(
            DataBindingUtil.inflate(
                inflater
                , R.layout.layout_nasa_news_list_item, parent, false
            )
        )
    }
    override fun getItemCount(): Int {
        return nasaNewsList.size
    }
    override fun onBindViewHolder(holder: NasaNewsListViewHolder, position: Int) {
        holder.layoutNasaNewsListItemBinding.nasanewsitem = nasaNewsList[position]
        holder.itemView.setOnClickListener {
            nasaNewsItemClickListener.onItemClick(position)
        }
    }
}