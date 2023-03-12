package com.shamardn.weather.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shamardn.weather.R
import com.shamardn.weather.databinding.FragmentHomeBinding
import com.shamardn.weather.ui.adapters.HomeAdapter
import com.shamardn.weather.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    lateinit var homeAdapter: HomeAdapter
    private val homeList: MutableList<HomeItem<Any>> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
        }

        collectHomeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.latitude.observe(this) {
            Toast.makeText(requireContext(), "lat = $it", Toast.LENGTH_SHORT).show()
        }
        viewModel.longitude.observe(this) {
            Toast.makeText(requireContext(),"lon = $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun collectHomeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.homeState.collectLatest {
                if (it.isSuccess) {
                    homeList.add(HomeItem(it.currentWeatherUiState, HomeItemType.TYPE_HEADER))
                    homeList.add(HomeItem(it.hourlyUiState, HomeItemType.TYPE_HOUR))
                    homeList.add(HomeItem(it.dailyUiState, HomeItemType.TYPE_DAY))
                    homeList.add(HomeItem(it.currentWeatherUiState, HomeItemType.TYPE_DETAILS))
                    setAdapter()
                    delay(2000)
                    binding.loading.visibility = View.GONE
                    binding.error.visibility = View.GONE
                    binding.rvHome.visibility = View.VISIBLE

                } else if (it.isLoading) {
                    binding.loading.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                    binding.rvHome.visibility = View.GONE
                } else if (it.isFailed) {
                    binding.error.visibility = View.VISIBLE
                    binding.loading.visibility = View.GONE
                    binding.rvHome.visibility = View.GONE
                }
            }
        }
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter(homeList)
        binding.rvHome.adapter = homeAdapter
    }
}