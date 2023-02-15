@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.compose

import androidx.compose.foundation.layout.padding
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
import com.yyttrium.oriplanner.ui.compose.content.*

sealed class Screen(val route: String) {
    object SprintView: Screen("SprintView")
    object TaskView: Screen("TaskView")
    object GoalView: Screen("GoalView")

    // Concatenate ID to end of string to complete route
    object SprintInsert: Screen("SprintInsert/")
    object TaskInsert: Screen("TaskInsert/")
    object GoalInsert: Screen("GoalInsert/")

    object Settings: Screen("Settings")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    sprintViewModel: ISprintViewModel,
    taskViewModel: ITaskViewModel,
    goalViewModel: IGoalViewModel,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = {
            OriTopAppBar(
                settingsSelected = (currentDestination?.route == Screen.Settings.route)
            )
        },

        bottomBar = {
            OriNavigationBar(
                currentDestination = currentDestination,
                onSprintsClicked = {
                    navController.navigate(Screen.SprintView.route) {
                        launchSingleTop = true // Don't navigate if already at
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
                OriFloatingActionButton(
                    onClick = {
                        when (currentDestination?.route) {
                            Screen.SprintView.route ->
                                navController.navigate(Screen.SprintInsert.route + "0") {
                                    launchSingleTop = true
                                }
                            Screen.TaskView.route ->
                                navController.navigate(Screen.TaskInsert.route + "0") {
                                    launchSingleTop = true
                                }
                            Screen.GoalView.route ->
                                navController.navigate(Screen.GoalInsert.route + "0") {
                                    launchSingleTop = true
                                }
                        }
                    }
                )
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

                    // Sprint Insert
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

                    // Task Insert
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
                        content = {
                            GoalView(
                                goalViewModel,
                                taskViewModel,
                                navController
                            )
                        }
                    )

                    // Goal Insert
                    composable(
                        route = Screen.GoalInsert.route + "{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType }),
                        content = { backStackEntry ->
                            GoalInsert(
                                goalViewModel = goalViewModel,
                                id = backStackEntry.arguments!!.getInt("id"),
                                navController = navController
                            )
                        }
                    )
                }
            }
        }
    )
}