package com.yyttrium.oriplanner.ui.compose.content

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yyttrium.oriplanner.data.*
import com.yyttrium.oriplanner.ui.compose.Screen
import com.yyttrium.oriplanner.ui.compose.content.components.OriCard
import com.yyttrium.oriplanner.ui.compose.content.components.OriCheckbox
import java.time.LocalDate

@Composable
fun SprintView(
    sprintViewModel: ISprintViewModel,
    navController: NavController
) {
    val allSprints by sprintViewModel.getAll.collectAsState(initial = listOf())

    LazyColumn {
        items(allSprints.size) { index ->
            var expanded by remember { mutableStateOf(false) }
            val sprint = allSprints[index]

            Row(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    OriCard(
                        expanded = expanded,
                        overdue = (sprint.sprintDue == LocalDate.now().toString()),
                        title = sprint.sprintName,
                        desc = sprint.sprintDesc,
                        onClick = { expanded = !expanded },
                        onLongClick = {
                            navController.navigate(
                                Screen.SprintInsert.route + sprint.sprintId.toString()
                            )
                        }
                    )
                }

                OriCheckbox(
                    checked = sprint.sprintComp,
                    expanded = expanded,
                    onCheckedChange = {
                        sprintViewModel.insert(
                            Sprint(
                                sprintId = sprint.sprintId,
                                sprintName = sprint.sprintName,
                                sprintDesc = sprint.sprintDesc,
                                sprintDue = sprint.sprintDue,
                                sprintComp = !sprint.sprintComp
                            )
                        )
                    },
                    onDelete = {
                        sprintViewModel.delete(sprint)
                    }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(64.dp))
}