@file:OptIn(ExperimentalFoundationApi::class)

package com.yyttrium.oriplanner.ui.components.content

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yyttrium.oriplanner.data.*
import com.yyttrium.oriplanner.ui.components.Screen
import java.time.LocalDate

@Composable
fun SprintView(
    sprintViewModel: ISprintViewModel,
    navController: NavController
) {
    val allSprints by sprintViewModel.getAll.collectAsState(initial = listOf())

    // dynamically render only list items on screen
    LazyColumn {
        items(allSprints.size) { index ->
            var expanded by remember { mutableStateOf(false) }
            val sprint = allSprints[index]

            // container for both card and icons
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
                // card containing Sprint information
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .combinedClickable(
                            onClick = { expanded = !expanded },
                            onLongClick = {
                                navController.navigate(
                                    Screen.SprintInsert.route +
                                            sprint.sprintId.toString()
                                )
                            }
                        ),
                    colors = CardDefaults.cardColors(
                        // TODO temp fix for overdue aesthetic
                        containerColor =
                        if (sprint.sprintDue == LocalDate.now().toString())
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.error
                    )
                ) {

                    // container for both title and description
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        // title
                        Text(
                            text = sprint.sprintName,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.titleLarge.copy()
                        )
                        // description, collapsible
                        if (expanded) {
                            if ((sprint.sprintDesc != null) && (sprint.sprintDesc != "")) {
                                Surface(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = MaterialTheme.shapes.medium
                                ) {
                                    Text(
                                        text = sprint.sprintDesc,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }

                    }
                }

                // container for checkbox and delete icon
                Column(
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    // checkbox
                    Checkbox(
                        checked = sprint.sprintComp,
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
                        }
                    )
                    // delete button
                    // TODO add confirmation dialog
                    if (expanded) {
                        IconButton(
                            onClick = { sprintViewModel.delete(sprint) },
                            modifier = Modifier,
                            enabled = sprint.sprintComp,
                            content = { Icon(Icons.Rounded.Delete, null) }
                        )
                    }
                }
            }
        }
    }
}