package com.yyttrium.oriplanner.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SprintDao {
    // order sprints by due_date, title
    @Query("SELECT * FROM sprints ORDER BY due_date, name")
    fun getAll(): Flow<List<Sprint>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sprint: Sprint)

    // TODO remove update and insert.replace instead?
    @Update
    suspend fun update(sprint: Sprint)

    @Delete
    suspend fun delete(sprint: Sprint)
}

@Dao
interface TaskDao {
    // order tasks by completion, due date, title
    @Query("SELECT * FROM tasks ORDER BY complete, due_date, name")
    fun getAll(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}

@Dao
interface GoalDao {
    // order goals by title
    @Query("SELECT * FROM goals ORDER BY name")
    fun getAll(): Flow<List<Goal>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(goal: Goal)

    @Update
    suspend fun update(goal: Goal)

    @Delete
    suspend fun delete(goal: Goal)
}