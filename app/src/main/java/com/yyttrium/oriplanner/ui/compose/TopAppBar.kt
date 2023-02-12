@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import com.yyttrium.oriplanner.R
import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme


@Composable
fun OriTopAppBar(
    settingsSelected: Boolean = false
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.titleLarge
            )
        },

        actions = {
            IconToggleButton(
                checked = settingsSelected,
                onCheckedChange = {}
            ) {
                Icon(
                    imageVector =
                    if (settingsSelected)
                        Icons.Filled.Settings
                    else
                        Icons.Outlined.Settings,
                    contentDescription = stringResource(R.string.icon_settings)
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewTopAppBarHome() {
    ORIPlannerTheme {
        OriTopAppBar(
            settingsSelected = false
        )
    }
}

@Preview
@Composable
fun PreviewTopAppBarSettings() {
    ORIPlannerTheme {
        OriTopAppBar(
            settingsSelected = true
        )
    }
}