package com.asad.noteapp.features.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
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
fun RowScope.HomeToolbarComponent(
    uiState: State<HomeUiState>,
    onSearchChanged: (String) -> Unit,
    onSearchBarCloseClicked: () -> Unit,
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
                value = uiState.value.searchQuery ?: "",
                onValueChange = {
                    if ((uiState.value.searchQuery?.length ?: 0) <= 15)
                        onSearchChanged(it)
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
                        contentDescription = stringResource(R.string.search_icon)
                    )
                },

                trailingIcon = {
                    uiState.value.searchQuery?.let {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.close_icon),
                            modifier = Modifier.clickable { onSearchBarCloseClicked() }
                        )
                    }
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
        HomeToolbarComponent(
            uiState = remember { mutableStateOf(HomeUiState()) },
            onLayoutChanged = {},
            onMenuClicked = {},
            onSearchChanged = {},
            onSearchBarCloseClicked = {}
        )
    }
}