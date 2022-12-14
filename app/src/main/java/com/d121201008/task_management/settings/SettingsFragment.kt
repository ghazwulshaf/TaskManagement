package com.d121201008.task_management.settings

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.d121201008.task_management.R
import com.d121201008.task_management.create.CreateViewModelFactory
import com.d121201008.task_management.database.TaskDatabase
import com.d121201008.task_management.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var builder: AlertDialog.Builder
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = SettingsViewModelFactory(dataSource, application)

        val settingsViewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)

        binding.settingsViewModel = settingsViewModel

        builder = AlertDialog.Builder(requireContext())

        binding.buttonReset.setOnClickListener {
            builder.setTitle("Alert")
                .setMessage("Are you sure you want to reset data?")
                .setCancelable(true)
                .setPositiveButton("Yes") { _, _ ->
                    settingsViewModel.clearData()
                    Toast.makeText(requireContext(), "Successfully reset!", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No") { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .setNeutralButton("Help") { dialogInterface, _ ->
                    dialogInterface.cancel()

                    val helpBuilder = AlertDialog.Builder(requireContext())

                    helpBuilder.setTitle("Help")
                        .setMessage("Selecting \"Yes\" will delete all your task data")
                        .setCancelable(true)
                        .setPositiveButton("Ok") { dialogInterface, _ ->
                            dialogInterface.cancel()
                        }
                        .show()
                }
                .show()
        }

        return binding.root
    }
}