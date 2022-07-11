package com.example.kmmpoc
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class GalleryRepository(databaseDriverFactory: DatabaseDriverFactory) {
    val httpClient: HttpClient = HttpClient()
    private val database = Database(databaseDriverFactory)

    suspend fun get() : List<Gallery>{
        try {
            val response = httpClient.get("https://picsum.photos/v2/list")
            val galleries = Json.decodeFromString<List<Gallery>>(response.bodyAsText())
            database.createGalleries(galleries)
            return galleries
        } catch (e: Exception) {
            return database.getAllGalleries()
        }
    }
}

@Serializable
data class Gallery(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerialName("download_url")
    val downloadUrl: String)
