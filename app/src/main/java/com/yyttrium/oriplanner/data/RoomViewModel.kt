package com.yyttrium.oriplanner.data

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ISprintViewModel {
    var id: Int
    val getAll: Flow<List<Sprint>>
    val getSprint: Flow<Sprint>
    fun insert(sprint: Sprint)
    fun delete(sprint: Sprint)
}

interface ITaskViewModel {
    var id: Int
    var goalId: Int
    val getAll: Flow<List<Task>>
    val getTask: Flow<Task>
    val getTaskByGoal: Flow<List<Task>>
    fun insert(task: Task)
    fun delete(task: Task)
}

interface IGoalViewModel {
    var id: Int
    val getAll: Flow<List<Goal>>
    val getGoal: Flow<Goal>
    fun insert(goal: Goal)
    fun delete(goal: Goal)
}

@HiltViewModel
class SprintViewModel
@Inject constructor(
    private val sprintRepository: SprintRepository
) : ViewModel(), ISprintViewModel {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override var id = 0

    override val getAll: Flow<List<Sprint>>
        get() = sprintRepository.getAll()

    override val getSprint: Flow<Sprint>
        get() = sprintRepository.getSprint(id)

    override fun insert(sprint: Sprint) {
        ioScope.launch {
            sprintRepository.insert(sprint)
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

    override var id = 0
    override var goalId = 0

    override val getAll: Flow<List<Task>>
        get() = taskRepository.getAll()

    override val getTask: Flow<Task>
        get() = taskRepository.getTask(id)

    override val getTaskByGoal: Flow<List<Task>>
        get() = taskRepository.getTaskByGoal(goalId)

    override fun insert(task: Task) {
        ioScope.launch {
            taskRepository.insert(task)
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

    override var id = 0

    override val getAll: Flow<List<Goal>>
        get() = goalRepository.getAll()

    override val getGoal: Flow<Goal>
        get() = goalRepository.getGoal(id)

    override fun insert(goal: Goal) {
        ioScope.launch {
            goalRepository.insert(goal)
        }
    }

    override fun delete(goal: Goal) {
        ioScope.launch {
            goalRepository.delete(goal)
        }
    }
}
