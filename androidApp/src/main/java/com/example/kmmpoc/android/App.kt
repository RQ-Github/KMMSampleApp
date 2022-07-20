package com.example.kmmpoc.android

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache

class App: Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
    }

    override fun newImageLoader(): ImageLoader {
        val imageLoader = ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(1.0)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(1.0)
                    .build()
            }
            .build()
        return imageLoader
    }
}