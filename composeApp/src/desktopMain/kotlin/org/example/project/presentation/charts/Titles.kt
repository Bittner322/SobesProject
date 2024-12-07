package org.example.project.presentation.charts

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AxisTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        title,
        color = Color.Black,
        style = MaterialTheme.typography.subtitle1,
        modifier = modifier
    )
}

@Composable
fun AxisLabel(
    modifier: Modifier = Modifier,
    label: String
) {
    Text(
        label,
        color = Color.Black,
        style = MaterialTheme.typography.subtitle2,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}