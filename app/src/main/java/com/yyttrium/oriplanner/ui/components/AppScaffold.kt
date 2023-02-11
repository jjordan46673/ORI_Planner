@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yyttrium.oriplanner.data.IGoalViewModel
import com.yyttrium.oriplanner.data.ISprintViewModel
import com.yyttrium.oriplanner.data.ITaskViewModel

sealed class Screen(val route: String) {
    // Concatenate ID to end of string to complete route
    object SprintView: Screen("SprintView")
    object SprintInsert: Screen("SprintInsert/")
    object TaskView: Screen("TaskView")
    object TaskInsert: Screen("TaskInsert/")
    object GoalView: Screen("GoalView")
    object GoalInsert: Screen("GoalInsert")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    sprintViewModel: ISprintViewModel,
    taskViewModel: ITaskViewModel,
    goalViewModel: IGoalViewModel,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = modifier,

        topBar = {
            Header()
        },

        bottomBar = {
            Navigation(
                currentDestination = currentDestination,

                onSprintsClicked = {
                    navController.navigate(Screen.SprintView.route) {
                        // Don't navigate to if already at
                        launchSingleTop = true
                    }
                },
                onTasksClicked = {
                    navController.navigate(Screen.TaskView.route) {
                        launchSingleTop = true
                    }
                },
                onGoalsClicked = {
                    navController.navigate(Screen.GoalView.route) {
                        launchSingleTop = true
                    }
                }
            )
        },

        floatingActionButton = {
            if (
                arrayOf(Screen.SprintView.route, Screen.TaskView.route, Screen.GoalView.route)
                    .any { it == currentDestination?.route }
            ) {
                // TODO button disappears when scrolling down
                FloatingActionButton(
                    onClick = {
                        if (currentDestination?.route == Screen.SprintView.route) {
                            navController.navigate(
                                route = Screen.SprintInsert.route + "0"
                            ) {
                                launchSingleTop = true
                            }
                        } else if (currentDestination?.route == Screen.TaskView.route) {
                            navController.navigate(
                                route = Screen.TaskInsert.route + "0"
                            ) {
                                launchSingleTop = true
                            }
                        }
                    },
                    modifier = Modifier,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add item",
                        modifier = Modifier
                    )
                }
            }
        },

        floatingActionButtonPosition = FabPosition.End,

        content = { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding)
            ) {
                // Navigation host
                NavHost(
                    navController = navController,
                    startDestination = Screen.SprintView.route
                ) {
                    // Sprint View
                    composable(
                        route = Screen.SprintView.route,
                        content = { SprintView(sprintViewModel, navController) }
                    )

                    // Sprint Insert, with Router
                    composable(
                        route = Screen.SprintInsert.route + "{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType }),
                        content = { backStackEntry ->
                            SprintInsert(
                                sprintViewModel = sprintViewModel,
                                id = backStackEntry.arguments!!.getInt("id"),
                                navController = navController
                            )
                        }
                    )

                    // Task View
                    composable(
                        route = Screen.TaskView.route,
                        content = { TaskView(taskViewModel, navController) }
                    )

                    // Task Insert, with Router
                    composable(
                        route = Screen.TaskInsert.route + "{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType }),
                        content = { backStackEntry ->
                            TaskInsert(
                                taskViewModel = taskViewModel,
                                id = backStackEntry.arguments!!.getInt("id"),
                                navController = navController
                            )
                        }
                    )

                    // Goal View
                    composable(
                        route = Screen.GoalView.route,
                        content = { GoalView(goalViewModel) }
                    )
                }
            }
        }
    )
}