package com.example.freecustomerdev_testapp.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {
    private val key = "ERgFN9eZ60nWBttY79"
    private val secret = "Uf4w03jIsQMKJMks0jBF87E9vU45OYw04JJ0"
    private val client = HttpClient(OkHttp){
        defaultRequest { url("https://api.bybit.com/") }

        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun getAnnounce(): Announce{
        return client.get("v5/announcements/index?locale=en-US&limit=10").body()
    }
}