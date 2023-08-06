package com.bm.newsapps.ui.view.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bm.newsapps.R

class ArticleWebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_webview)

        val webView: WebView = findViewById(R.id.wv_article)
        val articleUrl = intent.getStringExtra("article_url")
        val articleAuthor = intent.getStringExtra("article_author")
        title = articleAuthor ?: "no author"

        if (!articleUrl.isNullOrBlank()) {
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(articleUrl)
            }
        }
    }
}
