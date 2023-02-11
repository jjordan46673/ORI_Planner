@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.data.*
import java.time.LocalDate

@Composable
fun SprintView(
    sprintViewModel: ISprintViewModel,
    navController: NavController
) {
    val allSprints by sprintViewModel.getAll.collectAsState(initial = listOf())

    // TODO view should be scrollable
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

@Composable
fun SprintInsert(
    sprintViewModel: ISprintViewModel,
    id: Int,
    navController: NavController
) {
    // TODO move from getAll to getSprint()
    /*
    attempt 1: "initial" is not well documented
    tried "null" with strange results
    tried returning a list of 1 items with strange results
    tried alternate "collect" commands with no success
    path - dao > repository > view model > content
    */
    val allSprints by sprintViewModel.getAll.collectAsState(initial = listOf())

    fun findSprint(id: Int): Sprint {
        var output = Sprint(sprintName = "", sprintDue = "")
        for (sprint in allSprints) {
            if (sprint.sprintId == id) {
                output = sprint
                break
            }
        }
        return output
    }

    val editSprint: Sprint =
        if (id != 0) findSprint(id)
        else Sprint(sprintName = "", sprintDue = "")

    val SprintId: Int = editSprint.sprintId
    val SprintComp: Boolean = editSprint.sprintComp

    var SprintName by remember { mutableStateOf("") }
    SprintName = editSprint.sprintName
    var SprintDesc by remember { mutableStateOf("") }
    SprintDesc = editSprint.sprintDesc ?: ""


    fun returnToView() {
        navController.navigate(Screen.SprintView.route)
    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (SprintId == 0) "Add Sprint" else "Edit Sprint",
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = stringResource(R.string.desc_sprint),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = stringResource(R.string.hint_sprint),
                modifier = Modifier.padding(4.dp),
                fontStyle = FontStyle.Italic
            )
            // TODO keyboard goes away on tap away
            TextField(
                value = SprintName,
                onValueChange = { SprintName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Name") },
                singleLine = true
            )
            TextField(
                value = SprintDesc,
                onValueChange = { SprintDesc = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Description") },
                singleLine = false
            )
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // TODO refuse if Name or Due is not filled out
                // save button
                FilledTonalButton(
                    onClick = {
                        sprintViewModel.insert(
                            Sprint(
                                sprintId = SprintId,
                                sprintName = SprintName,
                                sprintDesc = if (SprintDesc == "") null else SprintDesc,
                                sprintDue = LocalDate.now().toString(),
                                sprintComp = SprintComp
                            )
                        )
                        returnToView()
                    }
                ) {
                    Text(
                        stringResource(R.string.button_confirm),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // cancel button
                FilledTonalButton(
                    onClick = {
                        returnToView()
                    }
                ) {
                    Text(
                        stringResource(R.string.button_cancel),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun TaskView(
    taskViewModel: ITaskViewModel,
    navController: NavHostController
) {
    val allTasks by taskViewModel.getAll.collectAsState(initial = listOf())

    LazyColumn {
        items(allTasks.size) { index ->
            var expanded by remember { mutableStateOf(false) }
            val task = allTasks[index]

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

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .combinedClickable(
                            onClick = { expanded = !expanded },
                            onLongClick = {
                                navController.navigate(
                                    Screen.TaskInsert.route +
                                            task.taskId.toString()
                                )
                            }
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor =
                        if ((task.taskDue.compareTo(LocalDate.now().toString())) >= 0)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.error
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = task.taskName,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = task.taskDue.drop(5),
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.labelLarge
                        )
                        if (expanded) {
                            if ((task.taskDesc != null) && (task.taskDesc != "")) {
                                Surface(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = MaterialTheme.shapes.medium
                                ) {
                                    Text(
                                        text = task.taskDesc,
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

                Column(
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Checkbox(
                        checked = task.taskComp,
                        onCheckedChange = {
                            taskViewModel.insert(
                                Task(
                                    taskId = task.taskId,
                                    taskName = task.taskName,
                                    taskDesc = task.taskDesc,
                                    taskDue = task.taskDue,
                                    taskComp = !task.taskComp
                                )
                            )
                        }
                    )
                    if (expanded) {
                        IconButton(
                            onClick = { taskViewModel.delete(task) },
                            modifier = Modifier,
                            enabled = task.taskComp,
                            content = { Icon(Icons.Rounded.Delete, null) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TaskInsert(
    taskViewModel: ITaskViewModel,
    id: Int,
    navController: NavController
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val allTasks by taskViewModel.getAll.collectAsState(initial = listOf())

    fun findTask(id: Int): Task {
        var output = Task(taskName = "", taskDue = "")
        for (task in allTasks) {
            if (task.taskId == id) {
                output = task
                break
            }
        }
        return output
    }

    val editTask: Task =
        if (id != 0) findTask(id)
        else Task(taskName = "", taskDue = "")

    val TaskId: Int = editTask.taskId
    val TaskComp: Boolean = editTask.taskComp

    var TaskName by remember { mutableStateOf("") }
    TaskName = editTask.taskName
    var TaskDesc by remember { mutableStateOf("") }
    TaskDesc = editTask.taskDesc ?: ""
    var TaskDue by remember { mutableStateOf("") }
    TaskDue =
        if (editTask.taskDue == "") LocalDate.now().toString()
        else editTask.taskDue

    fun returnToView() {
        navController.navigate(Screen.TaskView.route)
    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (TaskId == 0) "Add Task" else "Edit Task",
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = stringResource(R.string.desc_task),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = stringResource(R.string.hint_task),
                modifier = Modifier.padding(4.dp),
                fontStyle = FontStyle.Italic
            )
            TextField(
                value = TaskName,
                onValueChange = { TaskName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Name") },
                singleLine = true
            )
            TextField(
                value = TaskDesc,
                onValueChange = { TaskDesc = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Description") },
                singleLine = false
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = formatDate(TaskDue),
                    onValueChange = {},
                    modifier = Modifier
                        .weight(3f)
                        .padding(8.dp),
                    readOnly = true,
                    label = { Text("Due By") },
                    singleLine = true
                )
                FilledTonalButton(
                    onClick = { showDatePicker = true },
                    modifier = Modifier
                        .weight(2f)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.EditCalendar,
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(
                    onClick = {
                        taskViewModel.insert(
                            Task(
                                taskId = TaskId,
                                taskName = TaskName,
                                taskDesc = if (TaskDesc == "") null else TaskDesc,
                                taskDue = TaskDue,
                                taskComp = TaskComp
                            )
                        )
                        returnToView()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.button_confirm),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                FilledTonalButton(
                    onClick = { returnToView() }
                ) {
                    Text(
                        text = stringResource(R.string.button_cancel),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        if (showDatePicker) {
            DatePicker(
                onDateSelected = {
                    TaskDue = it
                    showDatePicker = false
                },
                selectedDate = TaskDue
            )
        }
    }
}

@Composable
fun GoalView(
    goalViewModel: IGoalViewModel
) {
    Text(
        text = "sample_text, goal view"
    )
}