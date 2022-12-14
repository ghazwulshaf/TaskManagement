package com.d121201008.task_management.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("SELECT * FROM task_table ORDER BY category ASC")
    fun getAll(): LiveData<List<Task>>

    @Query("DELETE FROM task_table")
    suspend fun clear()
}