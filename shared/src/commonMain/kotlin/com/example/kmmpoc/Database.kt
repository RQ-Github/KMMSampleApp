package com.example.kmmpoc

import kotlinx.serialization.SerialName

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllGalleries()
        }
    }

    internal fun getAllGalleries(): List<Gallery> {
        return dbQuery.selectAllGalleries(::mapGallerySelecting).executeAsList()
    }

    private fun mapGallerySelecting(
        id: String,
        author: String,
        width: Int,
        height: Int,
        url: String,
        download_url: String
    ): Gallery {
        return Gallery(
            id = id,
            author = author,
            width = width,
            height = height,
            url = url,
            downloadUrl = download_url
        )
    }

    internal fun createGalleries(galleries: List<Gallery>) {
        dbQuery.transaction {
            galleries.forEach { gallery ->
                insertGallery(gallery)
            }
        }
    }

    private fun insertGallery(gallery: Gallery) {
        dbQuery.insertGallery(
            id = gallery.id,
            author = gallery.author,
            width = gallery.width,
            height = gallery.height,
            url = gallery.url,
            download_url = gallery.downloadUrl
        )
    }
}