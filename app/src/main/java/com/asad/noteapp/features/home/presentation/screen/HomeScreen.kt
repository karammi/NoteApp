package com.asad.noteapp.features.home.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asad.noteapp.R
import com.asad.noteapp.theme.util.WindowSize
import com.asad.noteapp.theme.util.WindowType
import com.asad.noteapp.theme.util.rememberWindowSize
import com.asad.noteapp.features.home.presentation.component.NoteItemComponent
import com.asad.noteapp.features.home.presentation.component.NoteToolbarComponent
import com.asad.noteapp.features.home.presentation.model.Note
import com.asad.noteapp.features.home.presentation.model.HomeUiState
import com.asad.noteapp.features.home.presentation.viewModel.HomeViewModel
import com.asad.noteapp.theme.util.Emoji


@Composable
fun HomeScreen(
    onAddNoteClicked: () -> Unit,
    onNoteClicked: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val windowSize = rememberWindowSize()

    HomeContent(
        uiState = uiState,
        windowSize = windowSize,
        onCreateNoteClicked = onAddNoteClicked,
        onNoteClicked = onNoteClicked,
        onLayoutChanged = viewModel::updateListViewLayout,
        onSearchChanged = viewModel::onSearchChanged,
        onMenuClicked = {}
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    uiState: State<HomeUiState>,
    windowSize: WindowSize,
    onCreateNoteClicked: () -> Unit,
    onNoteClicked: (Int) -> Unit,
    onLayoutChanged: (useGridLayout: Boolean) -> Unit,
    onSearchChanged: (String) -> Unit,
    onMenuClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                actions = {
                    NoteToolbarComponent(
                        uiState = uiState,
                        onSearchChanged = onSearchChanged,
                        onLayoutChanged = onLayoutChanged,
                        onMenuClicked = onMenuClicked
                    )
                },
                modifier = Modifier
                    .testTag(stringResource(R.string.top_app_bar))
                    .border(1.dp, Color.LightGray)
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValue ->

        if (uiState.value.notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(stringResource(R.string.bottom_actions))
                    .padding(paddingValue),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .border(1.dp, shape = RoundedCornerShape(12.dp), color = Color.LightGray)
                        .padding(24.dp)
                        .clickable { onCreateNoteClicked() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_menu_board),
                        contentDescription = stringResource(R.string.img_create_note),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(R.string.create_note_app, Emoji.eye),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 120.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = stringResource(R.string.recent_all_notes, Emoji.blackCat),
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp)
                    .align(alignment = Alignment.TopCenter)
            )

            LazyVerticalStaggeredGrid(
                state = rememberLazyStaggeredGridState(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 110.dp)
                    .align(alignment = Alignment.TopCenter),
                contentPadding = PaddingValues(horizontal = 8.dp),
                columns = StaggeredGridCells.Fixed(
                    if (uiState.value.isGridLayout) {
                        when (windowSize.width) {
                            WindowType.Compact -> 2
                            WindowType.Medium -> 3
                            WindowType.Expanded -> 4
                        }
                    } else {
                        when (windowSize.width) {
                            WindowType.Compact -> 1
                            WindowType.Medium -> 2
                            WindowType.Expanded -> 3
                        }
                    }
                ),
            ) {
                itemsIndexed(uiState.value.notes) { _: Int, item: Note ->
                    NoteItemComponent(
                        modifier = Modifier,
                        note = item,
                        onNoteClicked = { noteId -> onNoteClicked(noteId) }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .border(1.dp, Color.White)
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
                        contentDescription = stringResource(R.string.ic_label)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(text = stringResource(R.string.labels))

                    Spacer(modifier = Modifier.width(12.dp))

                    FloatingActionButton(
                        modifier = Modifier
                            .testTag(tag = stringResource(R.string.create_note_test_tag))
                            .size(48.dp),
                        onClick = onCreateNoteClicked,
                        shape = CircleShape,
                        containerColor = Color(0xFF2284F9)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.add_note),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun HomeContentPreview() {
    val homeUiState = remember { mutableStateOf(HomeUiState()) }
    HomeContent(
        uiState = homeUiState,
        windowSize = rememberWindowSize(),
        onCreateNoteClicked = {},
        onNoteClicked = {},
        onLayoutChanged = {},
        onMenuClicked = {},
        onSearchChanged = {}
    )
}