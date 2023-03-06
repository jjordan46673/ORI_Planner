package com.yyttrium.oriplanner.data

import kotlinx.coroutines.flow.Flow

class SprintRepository(private val sprintDao: SprintDao) {
    fun getAll(): Flow<List<Sprint>> = sprintDao.getAll()
    fun getSprint(id: Int): Flow<Sprint> = sprintDao.getSprint(id)
    suspend fun insert(sprint: Sprint) = sprintDao.insert(sprint)
    suspend fun delete(sprint: Sprint) = sprintDao.delete(sprint)
}

class TaskRepository(private val taskDao: TaskDao) {
    fun getAll(): Flow<List<Task>> = taskDao.getAll()
    fun getTask(id: Int): Flow<Task> = taskDao.getTask(id)
    fun getTaskByGoal(goalId: Int) = taskDao.getTaskByGoal(goalId)
    suspend fun insert(task: Task) = taskDao.insert(task)
    suspend fun delete(task: Task) = taskDao.delete(task)
}
class GoalRepository(private val goalDao: GoalDao) {
    fun getAll(): Flow<List<Goal>> = goalDao.getAll()
    fun getGoal(id: Int): Flow<Goal> = goalDao.getGoal(id)
    suspend fun insert(goal: Goal) = goalDao.insert(goal)
    suspend fun delete(goal: Goal) = goalDao.delete(goal)
}