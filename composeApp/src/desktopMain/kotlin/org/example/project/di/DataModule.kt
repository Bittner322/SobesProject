package org.example.project.di

import org.example.project.data.repository.DatabaseRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    factoryOf(::DatabaseRepository)
}