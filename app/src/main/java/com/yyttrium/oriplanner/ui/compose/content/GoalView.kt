package com.yyttrium.oriplanner.ui.compose.content

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yyttrium.oriplanner.data.*
import com.yyttrium.oriplanner.ui.compose.Screen
import com.yyttrium.oriplanner.ui.compose.content.components.OriCard

@Composable
fun GoalView(
    goalViewModel: IGoalViewModel,
    taskViewModel: ITaskViewModel,
    navController: NavController
) {
    val allGoals by goalViewModel.getAll.collectAsState(initial = listOf())
    val allTasks by taskViewModel.getAll.collectAsState(initial = listOf())

    LazyColumn {
        items(allGoals.size) { index ->
            var expanded by remember { mutableStateOf(false) }
            val goal = allGoals[index]

            OriCard(
                expanded = expanded,
                title = goal.goalName,
                desc = goal.goalDesc,
                progress = 0.6f,
                onClick = { expanded = !expanded },
                onLongClick = {
                    navController.navigate(
                        Screen.GoalInsert.route + goal.goalId.toString()
                    )
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(64.dp))
}