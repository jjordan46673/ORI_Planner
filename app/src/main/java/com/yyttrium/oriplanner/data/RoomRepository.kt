package com.yyttrium.oriplanner.data

import kotlinx.coroutines.flow.Flow

class SprintRepository(private val sprintDao: SprintDao) {
    fun getAll(): Flow<List<Sprint>> = sprintDao.getAll()
    suspend fun insert(sprint: Sprint) = sprintDao.insert(sprint)
    suspend fun update(sprint: Sprint) = sprintDao.update(sprint)
    suspend fun delete(sprint: Sprint) = sprintDao.delete(sprint)
}

class TaskRepository(private val taskDao: TaskDao) {
    fun getAll(): Flow<List<Task>> = taskDao.getAll()
    suspend fun insert(task: Task) = taskDao.insert(task)
    suspend fun update(task: Task) = taskDao.update(task)
    suspend fun delete(task: Task) = taskDao.delete(task)
}
class GoalRepository(private val goalDao: GoalDao) {
    fun getAll(): Flow<List<Goal>> = goalDao.getAll()
    suspend fun insert(goal: Goal) = goalDao.insert(goal)
    suspend fun update(goal: Goal) = goalDao.update(goal)
    suspend fun delete(goal: Goal) = goalDao.delete(goal)
}