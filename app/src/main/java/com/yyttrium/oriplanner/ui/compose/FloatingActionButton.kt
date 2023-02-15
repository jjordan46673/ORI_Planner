package com.yyttrium.oriplanner.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

@Composable
fun OriFloatingActionButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.icon_fab)
        )
    }
}

@Preview
@Composable
fun PreviewFloatingActionButton() {
    ORIPlannerTheme {
        OriFloatingActionButton {}
    }
}