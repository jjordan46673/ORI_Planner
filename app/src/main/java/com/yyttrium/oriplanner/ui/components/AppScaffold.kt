@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.yyttrium.oriplanner.data.IGoalViewModel
import com.yyttrium.oriplanner.data.ISprintViewModel
import com.yyttrium.oriplanner.data.ITaskViewModel
import com.yyttrium.oriplanner.data.Sprint

enum class Nav {
    Sprint, Task, Goal
}

enum class Dlg {
    AddSprint, EditSprint, RemoveSprint,
    AddTask, EditTask, RemoveTask,
    AddGoal, EditGoal, RemoveGoal,
    TaskGoalAssociate, Close
}

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    sprintViewModel: ISprintViewModel,
    taskViewModel: ITaskViewModel,
    goalViewModel: IGoalViewModel
) {
    var NavSelection by rememberSaveable { mutableStateOf(Nav.Sprint) }
    var DlgSelection by rememberSaveable { mutableStateOf(Dlg.Close) }

    Scaffold(
        modifier = modifier,
        topBar = {
            Header()
        },
        bottomBar = {
            Navigation(
                navSelection = NavSelection,
                onSprintsClicked = { NavSelection = Nav.Sprint },
                onTasksClicked = { NavSelection = Nav.Task },
                onGoalsClicked = { NavSelection = Nav.Goal }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                // TODO change onClick
                onClick = {
                    DlgSelection = Dlg.AddSprint
                },
                modifier = Modifier,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add item",
                    modifier = Modifier
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        //containerColor = ,
        //contentColor = ,
        content = { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding)
            ) {
                when (NavSelection) {
                    Nav.Sprint -> {
                        SprintView(sprintViewModel)
                    }
                    Nav.Task -> {
                        TaskView(taskViewModel)
                    }
                    Nav.Goal -> {
                        GoalView(goalViewModel)
                    }
                }
                if (DlgSelection == Dlg.AddSprint) {
                    AddSprintView(
                        sprintViewModel = sprintViewModel,
                        closeDialog = { DlgSelection = Dlg.Close}
                    )
                }
            }
        }
    )
}