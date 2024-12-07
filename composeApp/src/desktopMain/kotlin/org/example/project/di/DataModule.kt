package org.example.project.di

import DriverFactory
import createDatabase
import org.example.Database
import org.example.project.data.repository.DatabaseRepository
import org.example.sqldelight.homes.data.HomesQueries
import org.koin.dsl.module

val dataModule = module {
    single { DatabaseRepository(get()) }
    single { createDatabase(get()) }
    single { createHomesQueries(get()) }
    single { DriverFactory() }
}

private fun createHomesQueries(database: Database): HomesQueries {
    return database.homesQueries
}