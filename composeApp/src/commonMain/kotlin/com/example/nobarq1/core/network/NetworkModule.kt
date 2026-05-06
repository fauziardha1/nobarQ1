package com.example.nobarq1.core.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            defaultRequest {
                url(TMDBConfig.BASE_URL)
                header(HttpHeaders.Authorization, "Bearer ${TMDBConfig.TOKEN}")
                header(HttpHeaders.Accept, "application/json")
                url.parameters.append("language", "en")
            }
        }
    }
}
