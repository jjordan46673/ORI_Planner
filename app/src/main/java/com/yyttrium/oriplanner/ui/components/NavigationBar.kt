package com.yyttrium.oriplanner.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsRun
import androidx.compose.material.icons.rounded.DirectionsWalk
import androidx.compose.material.icons.rounded.Hiking
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme

@Composable
fun OriNavigationBar(
    currentDestination: NavDestination?,
    onSprintsClicked: () -> Unit,
    onTasksClicked: () -> Unit,
    onGoalsClicked: () -> Unit,
) {
    NavigationBar() {
        NavigationBarItem(
            selected = (currentDestination?.route == Screen.SprintView.route),
            onClick = onSprintsClicked,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.DirectionsRun,
                    contentDescription = stringResource(R.string.nav_sprint)
                )
            },
            label = { Text(text = stringResource(R.string.nav_sprint)) }
        )

        NavigationBarItem(
            selected = (currentDestination?.route == Screen.TaskView.route),
            onClick = onTasksClicked,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.DirectionsWalk,
                    contentDescription = stringResource(id = R.string.nav_task)
                )
            },
            label = { Text(text = stringResource(id = R.string.nav_task)) }
        )

        NavigationBarItem(
            selected = (currentDestination?.route == Screen.GoalView.route),
            onClick = onGoalsClicked,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Hiking,
                    contentDescription = stringResource(id = R.string.nav_goal)
                )
            },
            label = { Text(text = stringResource(id = R.string.nav_goal)) }
        )
    }
}

@Preview
@Composable
fun PreviewNavNone() {
    ORIPlannerTheme() {
        val preNav = NavDestination("")
        preNav.route = "FakeDestination"

        OriNavigationBar(
            currentDestination = preNav,
            onSprintsClicked = {},
            onTasksClicked = {},
            onGoalsClicked = {}
        )
    }
}

@Preview
@Composable
fun PreviewNavSprint() {
    ORIPlannerTheme() {
        val preNav = NavDestination("")
        preNav.route = "SprintView"

        OriNavigationBar(
            currentDestination = preNav,
            onSprintsClicked = {},
            onTasksClicked = {},
            onGoalsClicked = {}
        )
    }
}

@Preview
@Composable
fun PreviewNavTask() {
    ORIPlannerTheme() {
        val preNav = NavDestination("")
        preNav.route = "TaskView"

        OriNavigationBar(
            currentDestination = preNav,
            onSprintsClicked = {},
            onTasksClicked = {},
            onGoalsClicked = {}
        )
    }
}

@Preview
@Composable
fun PreviewNavGoal() {
    ORIPlannerTheme() {
        val preNav = NavDestination("")
        preNav.route = "GoalView"

        OriNavigationBar(
            currentDestination = preNav,
            onSprintsClicked = {},
            onTasksClicked = {},
            onGoalsClicked = {}
        )
    }
}