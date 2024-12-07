package org.example.project.di

import org.example.project.ui.screens.main.MainScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val mainScreenModule = module {
    factoryOf(::MainScreenViewModel)
}