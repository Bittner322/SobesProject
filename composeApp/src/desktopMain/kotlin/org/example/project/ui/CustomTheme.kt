package org.example.project.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    /*MaterialTheme(
      colors = if (darkTheme) DarkColors else LightColors,
      content = content
    )*/
}