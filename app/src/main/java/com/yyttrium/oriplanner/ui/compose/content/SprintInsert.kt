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
    id: Int,
    sprintViewModel: ISprintViewModel,
    navController: NavController,
) {
    sprintViewModel.id = id

    val sprint: Sprint =
        if (id != 0)
            sprintViewModel.getSprint.collectAsState(
                initial = Sprint(sprintName = "", sprintDue = "")
            ).value
        else
            Sprint(sprintName = "", sprintDue = "")

    var sprintName by remember { mutableStateOf("") }
    var sprintDesc by remember { mutableStateOf("") }

    val sprintId: Int = sprint.sprintId
    sprintName = sprint.sprintName
    sprintDesc = sprint.sprintDesc ?: ""
    val sprintComp: Boolean = sprint.sprintComp

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
                id = sprintId,
                type = "Sprint",
                body = stringResource(R.string.body_sprint),
                hint = stringResource(R.string.hint_sprint),
                name = sprintName,
                desc = sprintDesc,
                onNameChange = { sprintName = it },
                onDescChange = { sprintDesc = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            OriInsertNav(
                onSave = {
                    sprintViewModel.insert(
                        Sprint(
                            sprintId = sprintId,
                            sprintName = sprintName,
                            sprintDesc = if (sprintDesc == "") null else sprintDesc,
                            sprintDue = LocalDate.now().toString(),
                            sprintComp = sprintComp
                        )
                    )
                    navController.navigate(Screen.SprintView.route)
                },
                onExit = { navController.navigate(Screen.SprintView.route) }
            )
        }
    }
}