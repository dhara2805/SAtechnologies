package com.example.satechnologies

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.satechnologies.adapter.InspectionAdapter
import com.example.satechnologies.databinding.ActivityInspectionBinding
import com.example.satechnologies.viewmodel.InspectionViewModel

class InspectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInspectionBinding
    private val inspectionViewModel: InspectionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInspectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerViewCategories.layoutManager = LinearLayoutManager(this)

        inspectionViewModel.startInspection()
        observeViewModel()
    }

    private fun observeViewModel() {
        inspectionViewModel.inspection.observe(this,Observer{inspectionViewModel->
            inspectionViewModel?.let {
                binding.textViewInspectionArea.text = "Area : "+it.inspection.area.name
                binding.textViewInspectionType.text = "Type : "+it.inspection.inspectionType.name
            }
        })
        inspectionViewModel.categories.observe(this, Observer { categories ->
            categories?.let {
                binding.recyclerViewCategories.adapter = InspectionAdapter(it)
            }
        })

        inspectionViewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }
}