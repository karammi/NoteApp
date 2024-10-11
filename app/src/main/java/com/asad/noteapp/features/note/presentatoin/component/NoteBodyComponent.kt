package com.asad.noteapp.features.note.presentatoin.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asad.noteapp.R
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
            modifier = Modifier
                .fillMaxWidth()
                .testTag(stringResource(R.string.title_label)),
            value = title.value,
            onValueChange = onTitleChanged,
            placeholder = {
                Text(
                    text = stringResource(R.string.title_label),
                    modifier = Modifier.testTag(stringResource(R.string.title_test_tag))
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
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
                .testTag(stringResource(R.string.note_test_tag))
                .padding(bottom = 110.dp),
            value = noteBody.value,
            onValueChange = onNoteBodyChanged,
            placeholder = {
                Text(
                    text = stringResource(R.string.note_title),
                    modifier = Modifier.testTag(stringResource(R.string.note_place_holder_test_tag))
                )
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

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun NoteBodyComponentPreview() {
    NoteBodyComponent(
        uiState = NoteUiState(),
        onNoteBodyChanged = {},
        onTitleChanged = {}
    )
}