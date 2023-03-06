package com.yyttrium.oriplanner.ui.compose.content

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

    fun calculateProgress(tasks: List<Task>): Float {
        if (tasks.isEmpty()) return 0f
        val completeTasks = tasks.filter { task -> task.taskComp }
        return completeTasks.size.toFloat() / tasks.size.toFloat()
    }

    LazyColumn {
        items(allGoals.size) { index ->
            var expanded by remember { mutableStateOf(false) }
            val goal = allGoals[index]
            val tasks = allTasks
                .filter { it.taskGoal == goal.goalId }
                .sortedBy { it.taskName }
                .sortedBy { it.taskDue }

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OriCard(
                    expanded = expanded,
                    name = goal.goalName,
                    desc = goal.goalDesc,
                    progress = calculateProgress(tasks),
                    tasks = tasks.ifEmpty { null },
                    onClick = { expanded = !expanded },
                    onLongClick = {
                        navController.navigate(
                            Screen.GoalInsert.route + goal.goalId.toString()
                        )
                    }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(64.dp))
}