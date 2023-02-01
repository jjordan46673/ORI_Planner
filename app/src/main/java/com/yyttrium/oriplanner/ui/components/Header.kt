@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yyttrium.oriplanner.R
import androidx.compose.material3.*
import androidx.compose.ui.res.stringResource


@Composable
fun Header() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        modifier = Modifier,
        //navigationIcon = {},
        //actions = ,
        //scrollBehavior =
    )
}