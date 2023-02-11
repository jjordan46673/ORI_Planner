@file:OptIn(ExperimentalFoundationApi::class)

package com.yyttrium.oriplanner.ui.components.content

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yyttrium.oriplanner.data.*

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

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    )
                    .combinedClickable(
                        onClick = { expanded = !expanded },
                        onLongClick = {}
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = goal.goalName,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.titleLarge.copy()
                    )
                    LinearProgressIndicator(
                        progress = 0.7f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .padding(horizontal = 16.dp, vertical = 2.dp)
                    )
                    if (expanded) {
                        if ((goal.goalDesc != null) && (goal.goalDesc != "")) {
                            Surface(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text(
                                    text = goal.goalDesc,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}