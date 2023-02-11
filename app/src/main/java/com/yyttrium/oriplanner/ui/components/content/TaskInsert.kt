@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.components.content

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.data.*
import com.yyttrium.oriplanner.ui.components.DatePicker
import com.yyttrium.oriplanner.ui.components.Screen
import com.yyttrium.oriplanner.ui.components.formatDate
import java.time.LocalDate

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