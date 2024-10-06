package com.asad.noteapp.features.home.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asad.noteapp.R
import com.asad.noteapp.core.util.WindowSize
import com.asad.noteapp.core.util.WindowType
import com.asad.noteapp.core.util.rememberWindowSize
import com.asad.noteapp.features.note.domain.model.NoteModel
import com.asad.noteapp.features.home.presentation.viewModel.HomeUiState
import com.asad.noteapp.features.home.presentation.viewModel.HomeViewModel
import kotlinx.coroutines.launch
import kotlin.math.truncate


@Composable
fun HomeScreen(
    onAddNoteClicked: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val windowSize = rememberWindowSize()

    HomeContent(
        uiState = uiState,
        windowSize = windowSize,
        onAddNoteClicked = onAddNoteClicked,
        onLayoutChanged = viewModel::updateListViewLayout,
        onMenuClicked = {}
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContent(
    uiState: State<HomeUiState>,
    windowSize: WindowSize,
    onAddNoteClicked: () -> Unit,
    onLayoutChanged: (useGridLayout: Boolean) -> Unit,
    onMenuClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                actions = {
                    Spacer(modifier = Modifier.width(8.dp))

                    Image(
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .size(40.dp)
                            .clickable { },
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = "layout"
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    val textValue = remember {
                        mutableStateOf("")
                    }
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f),
                        contentAlignment = Alignment.Center,

                        ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(alignment = Alignment.Center),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Icon(
                                painter = painterResource(R.drawable.ic_search_normal),
                                contentDescription = "search icon"
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            BasicTextField(
                                value = textValue.value,
                                onValueChange = { textValue.value = it },
                                maxLines = 1,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textStyle = TextStyle().copy(
                                    platformStyle = PlatformTextStyle(
                                        includeFontPadding = false
                                    ),
                                    lineHeight = 40.sp,
                                    textAlign = TextAlign.Start,
                                    textDecoration = TextDecoration.None
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        modifier = Modifier.clickable { onLayoutChanged(!uiState.value.isGridLayout) },
                        painter = if (uiState.value.isGridLayout)
                            painterResource(R.drawable.ic_grid)
                        else painterResource(R.drawable.ic_row_vertical),
                        contentDescription = "grid_layout"
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        modifier = Modifier.clickable { onMenuClicked() },
                        painter = painterResource(R.drawable.ic_menu),
                        contentDescription = "menu"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                },
                modifier = Modifier.border(1.dp, Color.LightGray)
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValue ->

        if (uiState.value.notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .border(1.dp, shape = RoundedCornerShape(12.dp), color = Color.LightGray)
                        .padding(24.dp)
                        .clickable { onAddNoteClicked() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_menu_board),
                        contentDescription = "add task",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Create New Note",
                        textAlign = TextAlign.Center,
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
                text = "Recent All Note",
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
                    .padding(top = 24.dp)
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

                itemsIndexed(uiState.value.notes) { index: Int, item: NoteModel ->
                    NoteItem(
                        title = item.title ?: "",
                        note = item.note ?: "",
                        hasTag = ((index + 1) % 2) == 0,
                        hasReminder = ((index + 1) % 2) == 0,
                        tagName = "Work $index",
                        reminderTime = "Tomorrow, 18:00"
                    )
                }
            }
            /**
             *  keep it in mind that
             *  this can be implemented using bottom bar also inside this scaffold component
             **/
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .border(1.dp, Color.LightGray)
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
                        onClick = onAddNoteClicked,
                        shape = CircleShape,
                        containerColor = Color(0xFF2284F9)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    title: String,
    note: String,
    hasTag: Boolean = false,
    tagName: String? = null,
    hasReminder: Boolean = false,
    reminderTime: String? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .semantics { contentDescription = "note_item" }
            .clickable {},
        elevation = CardDefaults.cardElevation(defaultElevation = 50.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Text(
                text = note,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            if (hasTag || hasReminder) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (hasReminder)
                        TagItem(
                            label = reminderTime ?: "",
                            onClick = {}
                        )
                    if (hasTag)
                        TagItem(
                            onClick = {},
                            label = tagName ?: ""
                        )
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun NoteItemPreview(modifier: Modifier = Modifier) {
    val title: String = "Heli Wbsite Design"
    val note: String =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt \n"
    val hasTag: Boolean = true
    val hasReminder: Boolean = true
    val reminderTime: String = "Tomorrow, 18:00"
    val tagName: String = "Work"

    Column {
        NoteItem(
            modifier = Modifier,
            title = title,
            note = note,
            hasTag = hasTag,
            tagName = tagName,
            hasReminder = hasReminder,
            reminderTime = reminderTime
        )

        NoteItem(
            modifier = Modifier,
            title = title,
            note = note,
            hasTag = false,
            tagName = tagName,
            hasReminder = false,
            reminderTime = reminderTime
        )
    }
}

@Composable
fun TagItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String
) {
    SuggestionChip(
        modifier = modifier,
        onClick = onClick,
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
fun TagItemPreview() {
    TagItem(
        label = "work",
        onClick = {})
}


