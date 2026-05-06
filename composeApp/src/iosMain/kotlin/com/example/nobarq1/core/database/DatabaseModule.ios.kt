package com.example.nobarq1.core.database

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.nobarq1.database.NobarDatabase
import org.koin.dsl.module

actual val databaseModule = module {
    single {
        val driver = NativeSqliteDriver(NobarDatabase.Schema, "nobar.db")
        NobarDatabase(driver)
    }
}
