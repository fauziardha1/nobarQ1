package com.example.nobarq1.core.di

import com.example.nobarq1.core.database.databaseModule
import com.example.nobarq1.core.network.networkModule
import com.example.nobarq1.features.auth.data.AuthRepositoryImpl
import com.example.nobarq1.features.auth.domain.AuthRepository
import com.example.nobarq1.features.auth.ui.LoginViewModel
import com.example.nobarq1.features.auth.ui.ProfileViewModel
import com.example.nobarq1.features.detail.ui.DetailViewModel
import com.example.nobarq1.features.home.data.MovieRepository
import com.example.nobarq1.features.home.data.MovieRepositoryImpl
import com.example.nobarq1.features.home.ui.HomeViewModel
import com.example.nobarq1.features.liked.ui.LikedViewModel
import com.example.nobarq1.features.search.ui.SearchViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.koin.compose.viewmodel.dsl.viewModel

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<AuthRepository> { AuthRepositoryImpl() }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { LikedViewModel(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            networkModule,
            databaseModule,
            repositoryModule,
            viewModelModule
        )
    }
