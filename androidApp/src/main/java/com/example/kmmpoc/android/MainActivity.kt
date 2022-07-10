package com.example.kmmpoc.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kmmpoc.GalleryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val galleryRepository = GalleryRepository()
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.gallery_recycler_view)
        recyclerView.adapter = GalleryRecyclerViewAdapter(listOf())
        loadGalleries()
    }

    fun loadGalleries() {
        GlobalScope.launch {
            val list = galleryRepository.get()
            withContext(Dispatchers.Main) {
                (recyclerView.adapter as GalleryRecyclerViewAdapter).galleries = list
                recyclerView.adapter!!.notifyDataSetChanged();
            }
        }
    }
}

