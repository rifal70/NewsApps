package com.bm.newsapps.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bm.newsapps.R

import android.content.Intent
import android.widget.TextView
import android.widget.Toast

class CategoryActivity : AppCompatActivity() {

    private lateinit var categoryTextViews: Array<TextView>
    private lateinit var tvSearchArticle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        tvSearchArticle = findViewById(R.id.tv_search_article)

        categoryTextViews = arrayOf(
            findViewById(R.id.tv_business),
            findViewById(R.id.tv_entertainment),
            findViewById(R.id.tv_general),
            findViewById(R.id.tv_health),
            findViewById(R.id.tv_sports),
            findViewById(R.id.tv_technology),
            findViewById(R.id.tv_science)
        )

        for (textView in categoryTextViews) {
            textView.setOnClickListener {
                val category = textView.text.toString()

                Toast.makeText(this, "$category", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SourceActivity::class.java)
                intent.putExtra("category", category)
                startActivity(intent)
            }
        }

        tvSearchArticle.setOnClickListener {
            val intent = Intent(this, ArticleActivity::class.java)
            startActivity(intent)
        }
    }
}
