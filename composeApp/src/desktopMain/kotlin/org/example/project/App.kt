package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.project.di.dataModule
import org.example.project.ui.screens.MainScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin

@Composable
@Preview
fun App() {

    startKoin {
        modules(dataModule)
    }

    MaterialTheme {
        MainScreen()
    }
}