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
import com.bm.newsapps.data.model.Article
import com.bm.newsapps.ui.view.adapter.ArticleBySourceAdapter
import com.bm.newsapps.ui.viewmodel.MainViewModel
import com.bm.newsapps.ui.viewmodel.ViewModelFactory

class ArticleBySourceActivity : AppCompatActivity(), ArticleBySourceAdapter.OnItemClickListener {
    private lateinit var rvArticleBySource: RecyclerView
    private lateinit var articleBySourceAdapter: ArticleBySourceAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_by_source)

        val sourceId = intent.getStringExtra("source_id")
        val sourceName = intent.getStringExtra("source_name")

        title = sourceName ?: "no source"

        rvArticleBySource = findViewById(R.id.rv_article_by_source)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)

        articleBySourceAdapter = ArticleBySourceAdapter(emptyList(),this)

        rvArticleBySource.apply {
            layoutManager = LinearLayoutManager(this@ArticleBySourceActivity)
            adapter = articleBySourceAdapter
        }
        viewModel.getArticleBySource(sourceId.toString())

        viewModel.toastMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.articleList.observe(this) { articleList ->
            articleBySourceAdapter.updateData(articleList)
        }
    }
    override fun onItemClick(newsItem: Article) {
//        Toast.makeText(this, "${source.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ArticleWebViewActivity::class.java)
        intent.putExtra("article_url", newsItem.url)
        intent.putExtra("article_author", newsItem.author)
        startActivity(intent)
    }
}