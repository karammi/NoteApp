package com.asad.noteapp.features.note.presentatoin.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asad.noteapp.features.note.presentatoin.model.NoteUiState

@Composable
fun NoteBodyComponent(
    modifier: Modifier = Modifier,
    uiState: NoteUiState,
    onTitleChanged: (String) -> Unit,
    onNoteBodyChanged: (String) -> Unit
) {
    val title = remember(uiState.note) {
        mutableStateOf(uiState.note?.title ?: "")
    }

    val noteBody = remember(key1 = uiState.note) {
        mutableStateOf(uiState.note?.note ?: "")
    }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title.value,
            onValueChange = onTitleChanged,
            placeholder = { Text(text = "Title") },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 110.dp),
            value = noteBody.value,
            onValueChange = onNoteBodyChanged,
            placeholder = {
                Text(text = "Note")
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun NoteBodyComponentPreview() {
    NoteBodyComponent(
        uiState = NoteUiState(),
        onNoteBodyChanged = {},
        onTitleChanged = {}
    )
}