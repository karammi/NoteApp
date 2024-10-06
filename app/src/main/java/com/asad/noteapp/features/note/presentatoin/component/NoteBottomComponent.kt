package com.asad.noteapp.features.note.presentatoin.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.asad.noteapp.R

@Composable
fun NoteBottomComponent(
    modifier: Modifier = Modifier,
    onSaveClicked: () -> Unit
) {
    Box(
        modifier = modifier,
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

@Composable
fun NoteBottomComponent() {
    NoteBottomComponent (onSaveClicked = {})
}