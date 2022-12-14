package com.d121201008.task_management.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.d121201008.task_management.database.Task
import com.d121201008.task_management.database.TaskDao

class ListViewModel(private val database: TaskDao, application: Application) : AndroidViewModel(application) {
    val tasks: LiveData<List<Task>> = database.getAll()
}