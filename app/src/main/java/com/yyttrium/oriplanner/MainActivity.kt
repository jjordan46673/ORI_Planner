package com.yyttrium.oriplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.yyttrium.oriplanner.data.GoalViewModel
import com.yyttrium.oriplanner.data.SprintViewModel
import com.yyttrium.oriplanner.data.TaskViewModel
import com.yyttrium.oriplanner.ui.compose.AppScaffold
import com.yyttrium.oriplanner.ui.theme.ORIPlannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //instantiate view models
        val sprintViewModel: SprintViewModel by viewModels()
        val taskViewModel: TaskViewModel by viewModels()
        val goalViewModel: GoalViewModel by viewModels()

        setContent {
            ORIPlannerTheme {
                AppScaffold(
                    sprintViewModel = sprintViewModel,
                    taskViewModel = taskViewModel,
                    goalViewModel = goalViewModel
                )
            }
        }
    }
}