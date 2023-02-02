package com.yyttrium.oriplanner.data

import android.media.midi.MidiDevice.MidiConnection
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Calendar
import java.util.Calendar.DATE

@Entity(tableName = "sprints")
data class Sprint (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var sprintId: Int = 0,
    @ColumnInfo(name = "name") val sprintName: String,
    @ColumnInfo(name = "description") val sprintDesc: String? = null,
    // TODO what is the best datatype for this?
    @ColumnInfo(name = "due_date") val sprintDue: String,
    @ColumnInfo(name = "complete") val sprintComp: Boolean = false,
)

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var taskId: Int = 0,
    @ColumnInfo(name = "name") val taskName: String,
    @ColumnInfo(name = "description") val taskDesc: String? = null,
    // TODO what is the best datatype for this?
    @ColumnInfo(name = "due_date") val taskDue: String,
    @ColumnInfo(name = "complete") val taskComp: Boolean = false,
)

@Entity (tableName = "goals")
data class Goal (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var goalId: Int = 0,
    @ColumnInfo(name = "name") val goalName: String,
    @ColumnInfo(name = "description") val goalDesc: String?,
)

// TODO is this useful?
fun getCalendar(): Calendar {
    return Calendar.getInstance()
}

// TODO conjoined table for task/goal