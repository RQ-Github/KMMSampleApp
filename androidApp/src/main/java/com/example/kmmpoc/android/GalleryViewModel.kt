package com.example.kmmpoc.android

import android.app.Application
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmpoc.DatabaseDriverFactory
import com.example.kmmpoc.Gallery
import com.example.kmmpoc.GalleryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val galleryRepository = GalleryRepository(DatabaseDriverFactory(getApplication()))
    var galleries: MutableState<List<Gallery>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            galleries.value = galleryRepository.get()
        }
    }
}