package com.example.nobarq1.features.auth.data

import com.example.nobarq1.features.auth.domain.AuthRepository
import com.example.nobarq1.features.auth.domain.Profile

class AuthRepositoryImpl : AuthRepository {
    private var loggedIn = false

    override suspend fun login(email: String, password: String): Boolean {
        // Mock authentication
        loggedIn = email.isNotEmpty() && password.isNotEmpty()
        return loggedIn
    }

    override suspend fun getProfiles(): List<Profile> {
        return listOf(
            Profile(1, "Fauzi", "https://i.pravatar.cc/150?u=1"),
            Profile(2, "Kids", "https://i.pravatar.cc/150?u=2")
        )
    }

    override fun isLoggedIn(): Boolean = loggedIn
}
