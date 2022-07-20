package com.example.kmmpoc.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kmmpoc.DatabaseDriverFactory
import com.example.kmmpoc.Gallery
import com.example.kmmpoc.GalleryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val galleryRepository = GalleryRepository(DatabaseDriverFactory(this))
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

    companion object {
//        var picasso: Picasso? = null
    }
}



class GalleryRecyclerViewAdapter(var galleries: List<Gallery>): RecyclerView.Adapter<GalleryRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView
        val authorTextView: TextView
        val urlTextView: TextView

        init {
            imageView = view.findViewById(R.id.gallery_item_image_view)
            authorTextView = view.findViewById(R.id.gallery_item_author_text_view)
            urlTextView = view.findViewById(R.id.gallery_item_url_text_view)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_gallery_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: GalleryRecyclerViewAdapter.ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.authorTextView.text = galleries[position].author
        viewHolder.urlTextView.text = galleries[position].url

        Glide
            .with(viewHolder.imageView.context)
            .load(galleries[position].downloadUrl)
            .circleCrop()
            .into(viewHolder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = galleries.size
}


