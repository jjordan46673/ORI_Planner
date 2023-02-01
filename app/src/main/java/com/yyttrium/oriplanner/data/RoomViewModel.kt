package com.yyttrium.oriplanner.data

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ISprintViewModel {
    val getAll: Flow<List<Sprint>>
    fun insert(sprint: Sprint)
    fun update(sprint: Sprint)
    fun delete(sprint: Sprint)
}

interface ITaskViewModel {
    val getAll: Flow<List<Task>>
    fun insert(task: Task)
    fun update(task: Task)
    fun delete(task: Task)
}

interface IGoalViewModel {
    val getAll: Flow<List<Goal>>
    fun insert(goal: Goal)
    fun update(goal: Goal)
    fun delete(goal: Goal)
}

@HiltViewModel
class SprintViewModel
@Inject constructor(
    private val sprintRepository: SprintRepository
) : ViewModel(), ISprintViewModel {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override val getAll: Flow<List<Sprint>>
        get() = sprintRepository.getAll()

    override fun insert(sprint: Sprint) {
        ioScope.launch {
            sprintRepository.insert(sprint)
        }
    }

    override fun update(sprint: Sprint) {
        ioScope.launch {
            sprintRepository.update(sprint)
        }
    }

    override fun delete(sprint: Sprint) {
        ioScope.launch {
            sprintRepository.delete(sprint)
        }
    }
}

@HiltViewModel
class TaskViewModel
@Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel(), ITaskViewModel {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override val getAll: Flow<List<Task>>
        get() = taskRepository.getAll()

    override fun insert(task: Task) {
        ioScope.launch {
            taskRepository.insert(task)
        }
    }

    override fun update(task: Task) {
        ioScope.launch {
            taskRepository.update(task)
        }
    }

    override fun delete(task: Task) {
        ioScope.launch {
            taskRepository.delete(task)
        }
    }
}

@HiltViewModel
class GoalViewModel
@Inject constructor(
    private val goalRepository: GoalRepository
) : ViewModel(), IGoalViewModel {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override val getAll: Flow<List<Goal>>
        get() = goalRepository.getAll()

    override fun insert(goal: Goal) {
        ioScope.launch {
            goalRepository.insert(goal)
        }
    }

    override fun update(goal: Goal) {
        ioScope.launch {
            goalRepository.update(goal)
        }
    }

    override fun delete(goal: Goal) {
        ioScope.launch {
            goalRepository.delete(goal)
        }
    }
}