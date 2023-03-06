package com.yyttrium.oriplanner.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SprintDao {
    // GET all sprints
    @Query("SELECT * FROM sprints ORDER BY complete, due_date, name")
    fun getAll(): Flow<List<Sprint>>

    // GET sprint by id
    @Query("SELECT * FROM sprints WHERE id = :id")
    fun getSprint(id: Int): Flow<Sprint>

    // INSERT or UPDATE sprint
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sprint: Sprint)

    //DELETE sprint
    @Delete
    suspend fun delete(sprint: Sprint)
}

@Dao
interface TaskDao {
    // GET all tasks
    @Query("SELECT * FROM tasks ORDER BY complete, due_date, name")
    fun getAll(): Flow<List<Task>>

    // GET task by id
    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTask(id: Int): Flow<Task>

    // GET tasks by appropriate goal association
    @Query("SELECT * FROM tasks WHERE goal_id = null OR goal_id = :goal_id ORDER BY due_date, name")
    fun getTaskByGoal(goal_id: Int): Flow<List<Task>>

    // INSERT or UPDATE task
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    // DELETE task
    @Delete
    suspend fun delete(task: Task)
}

@Dao
interface GoalDao {
    // GET all goals
    @Query("SELECT * FROM goals ORDER BY name")
    fun getAll(): Flow<List<Goal>>

    // GET goal by id
    @Query("SELECT * FROM goals WHERE id = :id")
    fun getGoal(id: Int): Flow<Goal>

    // INSERT or UPDATE goal
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(goal: Goal)

    // DELETE goal
    @Delete
    suspend fun delete(goal: Goal)
}