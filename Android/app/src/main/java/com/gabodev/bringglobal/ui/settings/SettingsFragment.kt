package com.gabodev.bringglobal.ui.settings

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import com.gabodev.bringglobal.utils.autoCleared
import com.gabodev.bringglobal.databinding.SettingsFragmentBinding

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var binding: SettingsFragmentBinding by autoCleared()
    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}