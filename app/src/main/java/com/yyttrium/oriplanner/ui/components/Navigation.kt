package com.yyttrium.oriplanner.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navSelection: Nav,
    onSprintsClicked: () -> Unit,
    onTasksClicked: () -> Unit,
    onGoalsClicked: () -> Unit,
) {

    NavigationBar(modifier = modifier) {

        // Sprints
        NavigationBarItem(
            selected = (navSelection == Nav.Sprint),
            onClick = onSprintsClicked,
            icon = {
                Icon(
                    imageVector = Icons.Filled.DirectionsRun,
                    contentDescription = null
                )
            },
            modifier = Modifier,
            label = {
                Text(
                    text = stringResource(id = R.string.nav_sprint)
                )
            },
        )

        // Tasks
        NavigationBarItem(
            selected = (navSelection == Nav.Task),
            onClick = onTasksClicked,
            icon = {
                Icon(
                    imageVector = Icons.Filled.DirectionsWalk,
                    contentDescription = null
                )
            },
            modifier = Modifier,
            label = {
                Text(
                    text = stringResource(id = R.string.nav_task)
                )
            },
        )

        // Goals
        NavigationBarItem(
            selected = (navSelection == Nav.Goal),
            onClick = onGoalsClicked,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Hiking,
                    contentDescription = null
                )
            },
            modifier = Modifier,
            label = {
                Text(
                    text = stringResource(id = R.string.nav_goal)
                )
            },
        )
    }

}

@Preview
@Composable
fun PreviewNavigation() {
    ORIPlannerTheme {
        Navigation(
            navSelection = Nav.Sprint,
            onSprintsClicked = { },
            onTasksClicked = { },
            onGoalsClicked = { }
        )
    }
}