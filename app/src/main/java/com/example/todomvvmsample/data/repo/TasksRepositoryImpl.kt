package com.example.todomvvmsample.data.repo

import com.example.todomvvmsample.data.Task
import com.example.todomvvmsample.data.db.TaskDao
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl(
    private val taskDao: TaskDao
) : TasksRepository {
    override fun getTasks(): Flow<List<Task>> {
        return taskDao.getTasks()
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