package com.bnyro.contacts.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bnyro.contacts.util.ClipboardHelper

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactEntry(
    label: String,
    content: String,
    @StringRes type: Int? = null,
    onClick: () -> Unit = {}
) {
    val shape = RoundedCornerShape(20.dp)
    val context = LocalContext.current

    ElevatedCard(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clip(shape)
            .combinedClickable(
                onClick = onClick,
                onLongClick = {
                    val clipboardHelper = ClipboardHelper(context)
                    clipboardHelper.save(content)
                }
            )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = (if (type == null) label else "$label (${stringResource(type)})").uppercase(),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = content)
        }
    }
}
