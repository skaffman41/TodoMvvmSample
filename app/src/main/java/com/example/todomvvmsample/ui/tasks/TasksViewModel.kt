package com.example.todomvvmsample.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.todomvvmsample.data.repo.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksRepository: TasksRepository
) : ViewModel() {
    val tasks = tasksRepository.getTasks().asLiveData()
}
