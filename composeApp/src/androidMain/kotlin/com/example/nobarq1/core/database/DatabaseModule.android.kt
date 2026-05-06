package com.example.nobarq1.core.database

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.nobarq1.database.NobarDatabase
import org.koin.dsl.module

actual val databaseModule = module {
    single {
        val driver = AndroidSqliteDriver(NobarDatabase.Schema, get(), "nobar.db")
        NobarDatabase(driver)
    }
}
