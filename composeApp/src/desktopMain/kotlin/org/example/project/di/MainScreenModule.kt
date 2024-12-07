package org.example.project.di

import org.example.project.presentation.screens.main.MainScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val mainScreenModule = module {
    factoryOf(::MainScreenViewModel)
}