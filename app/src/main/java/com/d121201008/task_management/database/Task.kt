package com.d121201008.task_management.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var _id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "detail")
    var detail: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "create_time")
    var time: String
)
