package com.gabodev.bringglobal.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.view.ViewGroup
import android.app.AlertDialog
import android.util.Log
import androidx.core.os.bundleOf
import com.gabodev.bringglobal.R
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.gabodev.bringglobal.utils.Resource
import com.gabodev.bringglobal.utils.autoCleared
import com.gabodev.bringglobal.extensions.unAccent
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.home_fragment.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.add_city_dialog.view.*
import com.gabodev.bringglobal.databinding.HomeFragmentBinding

@AndroidEntryPoint
class HomeFragment : Fragment(), CitiesAdapter.CityItemListener{

    private var binding: HomeFragmentBinding by autoCleared()
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: CitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        btnFabAdd.setOnClickListener {
            // Inflate the dialog with custom view
            val mDialogView = LayoutInflater.from(activity).inflate(R.layout.add_city_dialog, null)
            // AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(activity)
                    .setView(mDialogView)
                    .setTitle("Add City")
            // Show dialog
            val  mAlertDialog = mBuilder.show()
            // Login button click of custom layout
            mDialogView.dialogAddBtn.setOnClickListener {
                // Dismiss dialog
                mAlertDialog.dismiss()
                // Get input value
                val cityName = mDialogView.dialogNameEt.text.toString().unAccent()
                // Get Information of city
                viewModel.getCity(
                    cityName,
                    context?.getString(R.string.app_units),
                    context?.getString(R.string.app_weather_api_id)
                )
                viewModel.city.observe(viewLifecycleOwner, {
                    Toast.makeText(requireContext(), "Successfully", Toast.LENGTH_SHORT).show()
                })
            }
            // Cancel button click of custom layout
            mDialogView.dialogCancelBtn.setOnClickListener {
                // Dismiss dialog
                mAlertDialog.dismiss()
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = CitiesAdapter(this)
        binding.citiesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.citiesRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.cities.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCity(cityName: String) {
        findNavController().navigate(
            R.id.action_citiesFragment_to_cityFragment,
            bundleOf("name" to cityName.unAccent())
        )
    }
}