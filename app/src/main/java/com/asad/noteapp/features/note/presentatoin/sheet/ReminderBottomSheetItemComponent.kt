package com.asad.noteapp.features.note.presentatoin.sheet

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ReminderBottomSheetItemComponent(
    modifier: Modifier = Modifier,
    title: String,
    detail: String? = null,
    @DrawableRes leadingResIcon: Int,
    @DrawableRes trailingResIcon: Int? = null,
    showDivider: Boolean = true,
    onItemClicked: () -> Unit
) {
    Row(
        modifier = modifier.clickable { onItemClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(leadingResIcon),
            contentDescription = "$title leading icon"
        )

        VerticalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp)
        )
        Text(
            text = title,
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            style = TextStyle(textAlign = TextAlign.Center)
        )

        Spacer(modifier = Modifier.weight(1f))

        when {
            !detail.isNullOrEmpty() -> Text(text = detail)
            trailingResIcon != null -> Icon(
                painter = painterResource(trailingResIcon),
                contentDescription = "Edit $title"
            )
        }
    }
    if (showDivider)
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
}