package com.jokku.weather.preferences

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jokku.weather.R
import com.jokku.weather.databinding.FragmentPreferencesBinding

class PreferencesFragment : Fragment() {

    private val viewModel: PreferencesViewModel by viewModels()

    private lateinit var viewDataBinding: FragmentPreferencesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentPreferencesBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }
}