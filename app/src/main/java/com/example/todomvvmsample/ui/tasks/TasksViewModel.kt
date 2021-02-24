package com.example.todomvvmsample.ui.tasks

import androidx.lifecycle.ViewModel
import com.example.todomvvmsample.data.repo.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class TasksViewModel constructor(
    private val tasksRepository: TasksRepository
) : ViewModel() {
}