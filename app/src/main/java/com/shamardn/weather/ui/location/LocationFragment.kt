package com.shamardn.weather.ui.location

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.shamardn.weather.R
import com.shamardn.weather.databinding.FragmentLocationBinding
import com.shamardn.weather.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment: BaseFragment<FragmentLocationBinding>() {
    override val layoutIdFragment = R.layout.fragment_location
    override val viewModel: LocationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)

        viewModel.viewModelScope.launch {
            val cityName = viewModel.city
            Toast.makeText(requireActivity(),cityName.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}