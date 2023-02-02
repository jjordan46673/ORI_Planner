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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yyttrium.oriplanner.R

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onSprintsClicked: () -> Unit,
    onTasksClicked: () -> Unit,
    onGoalsClicked: () -> Unit,
) {
    NavigationBar(modifier = modifier) {

        // sprints
        NavigationBarItem(
            selected = (currentDestination?.route == Screen.SprintView.route),
            onClick = onSprintsClicked,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.DirectionsRun,
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

        // tasks
        NavigationBarItem(
            selected = (currentDestination?.route == Screen.TaskView.route),
            onClick = onTasksClicked,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.DirectionsWalk,
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

        // goals
        NavigationBarItem(
            selected = (currentDestination?.route == Screen.GoalView.route),
            onClick = onGoalsClicked,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Hiking,
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