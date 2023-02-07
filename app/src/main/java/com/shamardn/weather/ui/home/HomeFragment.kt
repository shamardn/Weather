package com.shamardn.weather.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shamardn.weather.R
import com.shamardn.weather.databinding.FragmentHomeBinding
import com.shamardn.weather.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        setAdapter()
        collectHomeData()
    }

    private fun collectHomeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {
                Log.i("wsh: HomeFragment last", "${it}")
                homeAdapter.setItems(
                    mutableListOf(
                        HomeItem.HourlyWeather(it.hourlyUiState),
                        HomeItem.DailyWeather(it.dailyUiState),

                    )
                )
            }
        }
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter(mutableListOf())
        binding.rvHome.adapter = homeAdapter
    }
}