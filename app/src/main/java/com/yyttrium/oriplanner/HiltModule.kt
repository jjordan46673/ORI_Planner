package com.yyttrium.oriplanner

import android.app.Application
import com.yyttrium.oriplanner.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    //Provide DAOs

    @Singleton
    @Provides
    fun provideSprintDao(
        appDatabase: AppDatabase
    ) : SprintDao {
        return appDatabase.sprintDao()
    }

    @Singleton
    @Provides
    fun provideTaskDao(
        appDatabase: AppDatabase
    ) : TaskDao {
        return appDatabase.taskDao()
    }

    @Singleton
    @Provides
    fun provideGoalDao(
        appDatabase: AppDatabase
    ) : GoalDao {
        return appDatabase.goalDao()
    }

    // Provide app database

    @Singleton
    @Provides
    fun provideAppDatabase(
        app: Application
    ) : AppDatabase {
        return AppDatabase.getInstance(context = app)
    }

    // Provide repositories

    @Singleton
    @Provides
    fun provideSprintRepository(
        sprintDao: SprintDao
    ) : SprintRepository {
        return SprintRepository(sprintDao = sprintDao)
    }

    @Singleton
    @Provides
    fun provideTaskRepository(
        taskDao: TaskDao
    ) : TaskRepository {
        return TaskRepository(taskDao = taskDao)
    }

    @Singleton
    @Provides
    fun provideGoalRepository(
        goalDao: GoalDao
    ) : GoalRepository {
        return GoalRepository(goalDao = goalDao)
    }

}