package com.yyttrium.oriplanner.ui.compose.content.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

@Composable
fun OriInsertNav(
    onSave: () -> Unit,
    onExit: () -> Unit,
    altSave: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TODO refuse if Name or Due is not filled out
        Spacer(modifier = Modifier.weight(1f))

        FilledTonalButton(
            onClick = onSave,
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text =
                if (altSave)
                    stringResource(R.string.button_goal_tasks)
                else
                    stringResource(R.string.button_confirm),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        FilledTonalButton(
            onClick = onExit,
            modifier = Modifier.weight(3f)
        ) {
            Text(
                stringResource(R.string.button_cancel),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun PreviewInsertNav() {
    ORIPlannerTheme {
        Surface() {
            OriInsertNav(
                onSave = {},
                onExit = {}
            )
        }
    }
}

@Preview
@Composable
fun PreviewInsertNavGoals() {
    ORIPlannerTheme {
        Surface() {
            OriInsertNav(
                onSave = {},
                onExit = {},
                altSave = true
            )
        }
    }
}