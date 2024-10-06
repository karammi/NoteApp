package com.asad.noteapp.features.note.presentatoin.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asad.noteapp.R
import com.asad.noteapp.features.note.presentatoin.model.NoteUiState
import com.asad.noteapp.features.note.presentatoin.viewModel.NoteViewModel


@Composable
fun NoteScreen(
    id: Int? = null,
    viewModel: NoteViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    NoteContent(
        uiState = uiState.value,
        onTitleChanged = viewModel::onTitleChanged,
        onNoteChanged = viewModel::onNoteChanged,
        onSaveClicked = viewModel::onSaveClicked
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

    val title = uiState.title ?: ""
    val noteBody = uiState.note ?: ""


    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray),
                actions = {
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(48.dp)
                            .clickable { onBackClicked() },
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = "layout"
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { onReminderClicked() },
                        painter = painterResource(R.drawable.ic_alarm),
                        contentDescription = "reminder"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { },
                        painter = painterResource(R.drawable.ic_inbox),
                        contentDescription = "inbox"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
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
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
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
                    thickness = 2.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(bottom = 110.dp),
                    value = noteBody,
                    onValueChange = onNoteChanged,
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .align(alignment = Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_tag),
                        contentDescription = "ic_label"
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(text = "Labels")

                    Spacer(modifier = Modifier.width(12.dp))

                    FloatingActionButton(
                        modifier = Modifier
                            .size(48.dp),
                        onClick = onSaveClicked,
                        shape = CircleShape,
                        containerColor = Color(0xFF2284F9)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_tick_circle),
                            contentDescription = "add",
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NoteContentPreview(modifier: Modifier = Modifier) {
    val uiState = NoteUiState()
    NoteContent(uiState)
}