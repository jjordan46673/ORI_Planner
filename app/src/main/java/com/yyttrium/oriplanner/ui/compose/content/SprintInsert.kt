package com.yyttrium.oriplanner.ui.compose.content

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yyttrium.oriplanner.R
import com.yyttrium.oriplanner.data.*
import com.yyttrium.oriplanner.ui.compose.Screen
import com.yyttrium.oriplanner.ui.compose.content.components.OriInsertFields
import com.yyttrium.oriplanner.ui.compose.content.components.OriInsertNav
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
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OriInsertFields(
                id = SprintId,
                type = "Sprint",
                body = stringResource(R.string.body_sprint),
                hint = stringResource(R.string.hint_sprint),
                name = SprintName,
                desc = SprintDesc,
                onNameChange = { SprintName = it },
                onDescChange = { SprintDesc = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            OriInsertNav(
                onSave = {
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
                },
                onExit = { returnToView() }
            )
        }
    }
}