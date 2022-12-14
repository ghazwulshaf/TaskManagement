package com.d121201008.task_management.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d121201008.task_management.R
import com.d121201008.task_management.database.TaskDatabase
import com.d121201008.task_management.databinding.FragmentListBinding

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val application = requireNotNull(this.activity).application

        val dataSource = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = ListViewModelFactory(dataSource, application)

        val listViewModel = ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)

        binding.listViewModel = listViewModel
        listViewModel.tasks.observe(viewLifecycleOwner, Observer { task ->
            adapter.setData(task)
        })

        return binding.root
    }
}