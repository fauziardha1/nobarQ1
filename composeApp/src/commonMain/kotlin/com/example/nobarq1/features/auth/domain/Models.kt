package com.example.nobarq1.features.auth.domain

data class Profile(val id: Int, val name: String, val avatarUrl: String)

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
    suspend fun getProfiles(): List<Profile>
    fun isLoggedIn(): Boolean
}
