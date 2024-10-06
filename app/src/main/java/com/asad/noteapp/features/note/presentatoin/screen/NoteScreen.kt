package com.asad.noteapp.features.note.presentatoin.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asad.noteapp.features.note.presentatoin.component.NoteBodyComponent
import com.asad.noteapp.features.note.presentatoin.component.NoteBottomComponent
import com.asad.noteapp.features.note.presentatoin.component.NoteToolbarComponent
import com.asad.noteapp.features.note.presentatoin.model.NoteUiState
import com.asad.noteapp.features.note.presentatoin.viewModel.NoteViewModel


@Composable
fun NoteScreen(
    id: Int? = null,
    onBackClicked: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    NoteContent(
        uiState = uiState.value,
        onTitleChanged = viewModel::onTitleChanged,
        onNoteChanged = viewModel::onNoteChanged,
        onSaveClicked = viewModel::onSaveClicked,
        onBackClicked = onBackClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteContent(
    uiState: NoteUiState,
    onTitleChanged: (String) -> Unit = {},
    onNoteChanged: (String) -> Unit = {},
    onSaveClicked: () -> Unit = {},
    onReminderClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    NoteToolbarComponent(
                        onBackClicked = onBackClicked,
                        onReminderClicked = onReminderClicked
                    )
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(top = 120.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            NoteBodyComponent(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                uiState = uiState,
                onTitleChanged = onTitleChanged,
                onNoteChanged = onNoteChanged
            )

            NoteBottomComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .shadow(1.dp, shape = RoundedCornerShape(1.dp))
                    .align(alignment = Alignment.BottomCenter),
                onSaveClicked = onSaveClicked
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NoteContentPreview(modifier: Modifier = Modifier) {
    val uiState = NoteUiState()
    NoteContent(uiState)
}


