package com.bm.newsapps.ui.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bm.newsapps.BuildConfig
import com.bm.newsapps.R
import com.bm.newsapps.data.model.Source
import com.bm.newsapps.ui.view.adapter.SourceAdapter
import com.bm.newsapps.ui.viewmodel.MainViewModel
import com.bm.newsapps.ui.viewmodel.ViewModelFactory

class SourceActivity: AppCompatActivity(), SourceAdapter.OnItemClickListener {

    private lateinit var sourceRecyclerView: RecyclerView
    private lateinit var sourceAdapter: SourceAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source)
        val category = intent.getStringExtra("category")

        title = category ?: "no category"

        sourceRecyclerView = findViewById(R.id.rv_source)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)

        sourceAdapter = SourceAdapter(emptyList(),this)

        sourceRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SourceActivity)
            adapter = sourceAdapter
        }
        viewModel.fetchSources(category.toString())

        viewModel.toastMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.sources.observe(this) { sources ->
            sourceAdapter.updateData(sources)
        }
    }
    override fun onItemClick(source: Source) {
//        Toast.makeText(this, "${source.id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ArticleBySourceActivity::class.java)
        intent.putExtra("source_id", source.id)
        intent.putExtra("source_name", source.name)
        startActivity(intent)
    }
}