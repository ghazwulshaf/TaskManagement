package com.d121201008.task_management.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.d121201008.task_management.database.Task
import com.d121201008.task_management.database.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateViewModel(private val database: TaskDao, application: Application) : AndroidViewModel(application) {
    fun insertData(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            database.insert(task)
        }
    }
}