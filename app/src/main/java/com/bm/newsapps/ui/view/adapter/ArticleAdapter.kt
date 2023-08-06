package com.bm.newsapps.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bm.newsapps.R
import com.bm.newsapps.data.model.Article

class ArticleAdapter(
    private var newsList: List<Article>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<ArticleAdapter.NewsViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(newsItem: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = newsList.size

    fun updateData(newList: List<Article>) {
        newsList = newList
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textAuthor: TextView = itemView.findViewById(R.id.tv_author)
        private val textTitle: TextView = itemView.findViewById(R.id.tv_title)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = newsList[position]
                    itemClickListener.onItemClick(clickedItem)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(newsItem: Article) {
            textAuthor.text = "Author: ${newsItem.author}"
            textTitle.text = newsItem.title
        }
    }
}