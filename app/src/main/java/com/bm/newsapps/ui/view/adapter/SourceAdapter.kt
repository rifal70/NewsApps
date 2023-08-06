package com.bm.newsapps.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bm.newsapps.R
import com.bm.newsapps.data.model.Source

class SourceAdapter(private var newsList: List<Source>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<SourceAdapter.NewsViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(newsItem: Source)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_source, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = newsList.size

    fun updateData(newList: List<Source>) {
        newsList = newList
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.tv_source_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = newsList[position]
                    listener.onItemClick(clickedItem)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(newsItem: Source) {
            textTitle.text = "${newsItem.name} "
        }
    }
}
