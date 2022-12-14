package com.d121201008.task_management.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.d121201008.task_management.database.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(private val database: TaskDao, application: Application) : AndroidViewModel(application) {
    fun clearData() {
        viewModelScope.launch(Dispatchers.IO) {
            database.clear()
        }
    }
}