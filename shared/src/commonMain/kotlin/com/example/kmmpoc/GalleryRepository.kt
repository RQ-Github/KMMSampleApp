package com.example.kmmpoc
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class GalleryRepository {
    val httpClient: HttpClient = HttpClient()
    suspend fun get() : List<Gallery>{
        try {
            val response = httpClient.get("https://picsum.photos/v2/list")
            return Json.decodeFromString(response.bodyAsText())
        } catch (e: Exception) {
            val a = ""
            return listOf()
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
