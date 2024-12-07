package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.project.di.dataModule
import org.example.project.di.mainScreenModule
import org.example.project.presentation.screens.main.MainScreen
import org.example.project.presentation.screens.main.MainScreenViewModel
import org.koin.compose.koinInject
import org.koin.core.context.startKoin

@Composable
fun App() {
    startKoin {
        modules(
            dataModule,
            mainScreenModule
        )
    }

    val viewModel: MainScreenViewModel = koinInject()
    MaterialTheme {
       MainScreen(viewModel)
    }
}
