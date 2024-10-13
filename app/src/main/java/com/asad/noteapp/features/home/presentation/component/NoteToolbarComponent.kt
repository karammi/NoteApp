package com.asad.noteapp.features.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asad.noteapp.R
import com.asad.noteapp.features.home.presentation.model.HomeUiState

@Composable
fun RowScope.NoteToolbarComponent(
    uiState: State<HomeUiState>,
    onSearchChanged: (String?) -> Unit,
    onLayoutChanged: (useGridLayout: Boolean) -> Unit,
    onMenuClicked: () -> Unit
) {
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


            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = textValue.value,
                onValueChange = {
                    if (textValue.value.length <= 15)
                        textValue.value = it
                },
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
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_search_normal),
                        contentDescription = "search icon"
                    )
                },

                trailingIcon = {
                    if (textValue.value.isNotBlank())
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "search icon"
                        )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black
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
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun NoteToolbarComponentPreview() {
    Row(
        modifier = Modifier.padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        NoteToolbarComponent(
            uiState = remember { mutableStateOf(HomeUiState()) },
            onLayoutChanged = {},
            onMenuClicked = {},
            onSearchChanged = {}
        )
    }
}