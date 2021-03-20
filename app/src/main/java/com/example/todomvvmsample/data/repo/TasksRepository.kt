package com.example.todomvvmsample.data.repo

import com.example.todomvvmsample.data.Task
import com.example.todomvvmsample.ui.tasks.SortOrder
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getTasks(query: String, sortOrder: SortOrder, hideCompleted: Boolean): Flow<List<Task>>
    suspend fun insert(task: Task)
    suspend fun update(task: Task)
    suspend fun delete(task: Task)
}