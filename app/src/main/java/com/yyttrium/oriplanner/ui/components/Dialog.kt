@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.data.ISprintViewModel
import com.yyttrium.oriplanner.data.Sprint
import java.time.LocalDate

@Composable
fun AddSprintView(
    sprintViewModel: ISprintViewModel,
    sprint: Sprint? = null,
    closeDialog: () -> Unit
) {
    // TODO insert onConflict replace
    var SprintId by remember { mutableStateOf(sprint?.sprintId ?: 0) }
    var SprintName by remember { mutableStateOf(sprint?.sprintName ?: "") }
    var SprintDesc by remember { mutableStateOf(sprint?.sprintDesc ?: "") }
    var SprintComp by remember { mutableStateOf(sprint?.sprintComp ?: false) }

    Dialog(
        onDismissRequest = closeDialog,
        properties = DialogProperties(),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .fillMaxWidth(0.96f),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (SprintId == 0) "Add Sprint" else "Edit Sprint",
                    modifier = Modifier.padding(8.dp),
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
                TextField(
                    value = SprintName,
                    onValueChange = { SprintName = it },
                    modifier = Modifier.padding(8.dp),
                    label = { Text("Name") },
                    singleLine = true
                )
                TextField(
                    value = SprintDesc,
                    onValueChange = { SprintDesc = it },
                    modifier = Modifier.padding(8.dp),
                    label = { Text("Description") },
                    singleLine = false
                )
                Spacer(modifier = Modifier.weight(1f))
                FilledTonalButton(
                    onClick = {
                        sprintViewModel.insert(
                            Sprint(
                                sprintId = SprintId,
                                sprintName = SprintName,
                                sprintDesc = SprintDesc,
                                sprintDue = LocalDate.now().toString(),
                                sprintComp = SprintComp
                            )
                        )
                        SprintId = 0
                        SprintName = ""
                        SprintDesc = ""
                        SprintComp = false
                        closeDialog
                    }
                ) {
                    Text(
                        stringResource(R.string.button_confirm),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    //Icon(imageVector = Icons.Filled.Check, null)
                }

            }
        }
    }
}

@Composable
fun EditSprintView() {

}

@Composable
fun RemoveSprintView() {

}

@Composable
fun AddTaskView() {

}

@Composable
fun EditTaskView() {

}

@Composable
fun RemoveTaskView() {

}

@Composable
fun AddGoalView() {

}

@Composable
fun EditGoalView() {

}

@Composable
fun RemoveGoalView() {

}

@Composable
fun TaskGoalAssociateView() {

}