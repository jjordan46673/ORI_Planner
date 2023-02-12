@file:OptIn(ExperimentalMaterial3Api::class)

package com.yyttrium.oriplanner.ui.compose.content

import androidx.compose.foundation.layout.*
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
import com.yyttrium.oriplanner.ui.compose.Screen
import java.time.LocalDate

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
    // TODO view should be scrollable
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