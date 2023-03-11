package com.shamardn.weather.ui.location

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.shamardn.weather.R
import com.shamardn.weather.databinding.FragmentLocationBinding
import com.shamardn.weather.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationFragment @Inject constructor(
) : BaseFragment<FragmentLocationBinding>() {
    override val layoutIdFragment = R.layout.fragment_location
    override val viewModel: LocationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@LocationFragment.viewModel

        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.findLocation(requireActivity())
    }
}