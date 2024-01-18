package com.example.freecustomerdev_testapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient: NetworkInterface {
    private val client = HttpClient(OkHttp){

        defaultRequest { url("https://api.bybit.com/") }

        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    override suspend fun getAnnounce(): AnnounceApi{
        return client.get("v5/announcements/index?locale=en-US&limit=10").body()
    }
}