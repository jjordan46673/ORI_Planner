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

@Composable
fun GoalInsert(
    id: Int,
    goalViewModel: IGoalViewModel,
    navController: NavController
) {
    goalViewModel.id = id

    val goal: Goal =
        if (id != 0)
            goalViewModel.getGoal.collectAsState(
                initial = Goal(goalName = "")
            ).value
        else Goal(goalName = "")

    var goalName by remember { mutableStateOf("") }
    var goalDesc by remember { mutableStateOf("") }

    val goalId: Int = goal.goalId
    goalName = goal.goalName
    goalDesc = goal.goalDesc ?: ""

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
                id = goalId,
                type = "Goal",
                body = stringResource(R.string.body_goal),
                hint = stringResource(R.string.hint_goal),
                name = goalName,
                desc = goalDesc,
                onNameChange = { goalName = it },
                onDescChange = { goalDesc = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            OriInsertNav(
                onSave = {
                    goalViewModel.insert(
                        Goal(
                            goalId = goalId,
                            goalName = goalName,
                            goalDesc = if (goalDesc == "") null else goalDesc,
                        )
                    )
                    navController.navigate(Screen.GoalTask.route + goalId.toString())
                },
                onExit = { navController.navigate(Screen.GoalView.route) },
                altSave = true
            )
        }
    }
}