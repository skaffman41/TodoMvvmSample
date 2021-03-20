package com.example.todomvvmsample.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todomvvmsample.data.PreferencesManager
import com.example.todomvvmsample.data.SortOrder
import com.example.todomvvmsample.data.repo.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    val searchQuery = MutableStateFlow("")

    val preferencesFlow = preferencesManager.preferencesFlow

    private val tasksFlow = combine(
        searchQuery,
        preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        tasksRepository.getTasks(
            query,
            filterPreferences.sortOrder,
            filterPreferences.hideCompleted
        )
    }

    fun onSortOrderSelected(sortOrder: SortOrder) {
        viewModelScope.launch {
            preferencesManager.updateSortOrder(sortOrder)
        }
    }

    fun onHideCompletedSelected(hideCompleted: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateHideCompleted(hideCompleted)
        }
    }

    val tasks = tasksFlow.asLiveData()
}
