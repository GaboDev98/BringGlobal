package com.gabodev.bringglobal.ui.city

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.view.ViewGroup
import com.gabodev.bringglobal.R
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.gabodev.bringglobal.utils.Resource
import com.gabodev.bringglobal.utils.autoCleared
import com.gabodev.bringglobal.data.entities.City
import com.gabodev.bringglobal.databinding.CityDetailFragmentBinding

@AndroidEntryPoint
class CityDetailFragment : Fragment() {
    
    private var binding: CityDetailFragmentBinding by autoCleared()
    private val viewModel: CityDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CityDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("name")?.let {
            viewModel.start(
                it,
                context?.getString(R.string.app_units),
                context?.getString(R.string.app_weather_api_id)
            )
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.city.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data != null) {
                        bindCity(it.data)
                        binding.progressBar.visibility = View.GONE
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun bindCity(city: City) {
        binding.tvCity.text = city.name
        binding.tvDescription.text = city.weather?.get(0)?.description
        binding.tvTemperature.text = "Temperature: ${city.main?.temp.toString()}°C"
        binding.tvTempMin.text = "Temp min: ${city.main?.temp_min.toString()}°C"
        binding.tvTempMax.text = "Temp max: ${city.main?.temp_max.toString()}°C"
        /*binding.status.text = character.status
        binding.gender.text = character.gender
        Glide.with(binding.root)
            .load(character.image)
            .transform(CircleCrop())
            .into(binding.image)*/
    }
}