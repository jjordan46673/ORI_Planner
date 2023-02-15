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
    goalViewModel: IGoalViewModel,
    id: Int,
    navController: NavController
) {
    val allGoals by goalViewModel.getAll.collectAsState(initial = listOf())

    fun findGoal(id: Int): Goal {
        var output = Goal(goalName = "")
        for (goal in allGoals) {
            if (goal.goalId == id) {
                output = goal
                break
            }
        }
        return output
    }

    val editGoal: Goal =
        if (id != 0) findGoal(id)
        else Goal(goalName = "")

    val GoalId: Int = editGoal.goalId

    var GoalName by remember { mutableStateOf("") }
    GoalName = editGoal.goalName
    var GoalDesc by remember { mutableStateOf("") }
    GoalDesc = editGoal.goalDesc ?: ""


    fun returnToView() {
        navController.navigate(Screen.GoalView.route)
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
                id = GoalId,
                type = "Goal",
                body = stringResource(R.string.body_goal),
                hint = stringResource(R.string.hint_goal),
                name = GoalName,
                desc = GoalDesc,
                onNameChange = { GoalName = it },
                onDescChange = { GoalDesc = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            OriInsertNav(
                onSave = {
                    goalViewModel.insert(
                        Goal(
                            goalId = GoalId,
                            goalName = GoalName,
                            goalDesc = if (GoalDesc == "") null else GoalDesc,
                        )
                    )
                    returnToView()
                },
                onExit = { returnToView() }
            )
        }
    }
}