package com.example.todomvvmsample.data.repo

import com.example.todomvvmsample.data.Task
import com.example.todomvvmsample.data.db.TaskDao
import com.example.todomvvmsample.ui.tasks.SortOrder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TasksRepository {
    override fun getTasks(
        query: String,
        sortOrder: SortOrder,
        hideCompleted: Boolean
    ): Flow<List<Task>> {
        return taskDao.getTasks(query, sortOrder, hideCompleted)
    }

    override suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    override suspend fun update(task: Task) {
        taskDao.update(task)
    }

    override suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}