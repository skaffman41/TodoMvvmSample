package com.example.todomvvmsample.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todomvvmsample.R
import com.example.todomvvmsample.databinding.FragmentTasksBinding
import com.example.todomvvmsample.ui.adapters.TasksAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_tasks) {
    private val viewModel: TasksViewModel by viewModels()

    private lateinit var binding: FragmentTasksBinding
    private lateinit var tasksAdapter: TasksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTasksBinding.bind(view)
        tasksAdapter = TasksAdapter()

        with(binding) {
            with(rvTasks) {
                adapter = tasksAdapter
                setHasFixedSize(true)
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.tasks.observe(viewLifecycleOwner) {
            tasksAdapter.submitList(it)
        }
    }
}