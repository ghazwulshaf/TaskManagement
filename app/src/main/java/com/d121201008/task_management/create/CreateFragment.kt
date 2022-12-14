package com.d121201008.task_management.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.d121201008.task_management.R
import com.d121201008.task_management.database.Task
import com.d121201008.task_management.database.TaskDatabase
import com.d121201008.task_management.databinding.FragmentCreateBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateFragment : Fragment() {
    private var category: String?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCreateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource =  TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = CreateViewModelFactory(dataSource, application)

        val createViewModel = ViewModelProvider(this, viewModelFactory).get(CreateViewModel::class.java)

        binding.createViewModel = createViewModel

        val spinner: Spinner = binding.spinnerCategory
        ArrayAdapter.createFromResource(requireContext(), R.array.categories, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                category = spinner.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.buttonCreate.setOnClickListener {
            val title = binding.inputTitle.text.toString()
            val detail = binding.inputDetail.text.toString()

            val currentTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val time = currentTime.format(formatter).toString()

            if (title.isNotEmpty() && detail.isNotEmpty() && category!!.isNotEmpty()) {
                val task = Task(0, title, detail, category!!, time)
                createViewModel.insertData(task)

                binding.inputTitle.setText("")
                binding.inputDetail.setText("")
                Toast.makeText(requireContext(), "Successfully created!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Fill out all fields!", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}