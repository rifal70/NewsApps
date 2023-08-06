package com.bm.newsapps.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bm.newsapps.R
import com.bm.newsapps.ui.viewmodel.MainViewModel
import com.bm.newsapps.ui.viewmodel.ViewModelFactory
import com.bm.newsapps.ui.view.adapter.ArticleAdapter
import androidx.appcompat.widget.SearchView
import com.bm.newsapps.data.model.Article
import com.bm.newsapps.ui.view.adapter.ArticleBySourceAdapter

class ArticleActivity : AppCompatActivity(), ArticleAdapter.ItemClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)

        title = "Cari Artikel"

        searchView = findViewById(R.id.sb_article)
        recyclerView = findViewById(R.id.rv_article)
        articleAdapter = ArticleAdapter(emptyList(), this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ArticleActivity)
            adapter = articleAdapter
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    viewModel.searchNews(newText)
                }
                return true
            }
        })
        viewModel.toastMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.articleList.observe(this, Observer { articles ->
            articleAdapter.updateData(articles)
        })
    }

    override fun onItemClick(newsItem: Article) {
//        Toast.makeText(this, "${source.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ArticleWebViewActivity::class.java)
        intent.putExtra("article_url", newsItem.url)
        intent.putExtra("article_author", newsItem.author)
        startActivity(intent)
    }
}
