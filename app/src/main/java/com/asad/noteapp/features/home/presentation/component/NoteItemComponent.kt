package com.asad.noteapp.features.home.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asad.noteapp.features.home.presentation.model.Note


@Composable
fun NoteItemComponent(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Int) -> Unit,
//    hasTag: Boolean = false,
//    tagName: String? = null,
//    hasReminder: Boolean = false,
//    reminderTime: String? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .semantics { contentDescription = "note_item" }
            .clickable { onNoteClicked(note.id) },
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
                text = note.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )

            Text(
                text = note.note ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            if (note.hasTag || note.hasReminder) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (note.reminder != null && note.reminder != 0L)
                        TagItemComponent(label = note.reminderDateTime ?: "")
                    if (note.tags != null && note.tags != "")
                        TagItemComponent(label = note.tags)
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun NoteItemPreview() {
    val title: String = "Heli Wbsite Design"
    val note: String =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt \n"

    Column(modifier = Modifier.padding(vertical = 80.dp)) {
        NoteItemComponent(
            modifier = Modifier,
            note = Note(id = 1, title, note),
            onNoteClicked = {}
        )

        NoteItemComponent(
            modifier = Modifier,
            note = Note(id = 2, title, note),
            onNoteClicked = {}
        )
    }
}

