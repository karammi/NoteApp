package com.asad.noteapp.features.home.presentation.component

import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TagItemComponent(
    modifier: Modifier = Modifier,
    label: String
) {
    SuggestionChip(
        modifier = modifier,
        onClick = {},
        label = {
            Text(
                text = label,
                color = Color.White,
            )
        },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = Color(0xFF3384F9).copy(alpha = 0.25f),
            labelColor = Color(0xFF3384F9)
        )
    )
}

@Preview(showSystemUi = false)
@Composable
fun TagItemComponentPreview() {
    TagItemComponent(
        label = "work"
    )
}