package com.example.kmmpoc.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.kmmpoc.Gallery

class GalleryListActivity : ComponentActivity() {
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { 
            LazyColumn {
                items(
                    count = viewModel.galleries.value.size-10,
                    key = {
                        viewModel.galleries.value[it].id
                    },
                    itemContent = {
                    GalleryItem(gallery = viewModel.galleries.value[it])
                })
            }
        }
    }
}

@Composable
fun GalleryItem(gallery: Gallery) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RectangleShape
    ) {
        Row(modifier = Modifier.padding(all = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
           AsyncImage(
                model = gallery.downloadUrl,
                contentDescription = null,
                modifier = Modifier.size(60.dp, 60.dp).clip(CircleShape),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
                )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = gallery.author)
                Text(text = gallery.url)
            }
        }
    }

}
@Preview
@Composable
fun PreviewMessageCard() {
    GalleryItem(Gallery(id = "id", author = "author", width = 0, height = 0, url = "url", downloadUrl = ""))
}
