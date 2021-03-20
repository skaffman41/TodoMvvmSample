package com.example.todomvvmsample.ui.tasks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todomvvmsample.R
import com.example.todomvvmsample.databinding.FragmentTasksBinding
import com.example.todomvvmsample.ui.adapters.TasksAdapter
import com.example.todomvvmsample.util.onQueryTextChanged
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
        setHasOptionsMenu(true)
    }

    private fun observeViewModel() {
        viewModel.tasks.observe(viewLifecycleOwner) {
            tasksAdapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_tasks, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged { viewModel.searchQuery.value = it }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_by_name -> {

                true
            }

            R.id.action_sort_by_date -> {

                true
            }

            R.id.action_hide_completed_tasks -> {
                item.isChecked = !item.isChecked
                true
            }

            R.id.action_delete_all_completed_tasks -> {

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}