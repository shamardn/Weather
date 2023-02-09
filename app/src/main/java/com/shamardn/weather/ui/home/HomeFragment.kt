package com.shamardn.weather.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shamardn.weather.R
import com.shamardn.weather.databinding.FragmentHomeBinding
import com.shamardn.weather.ui.base.BaseFragment
import com.shamardn.weather.util.toHomeItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    lateinit var homeAdapter: HomeAdapter
    val homeList: MutableList<HomeItem<Any>> = mutableListOf()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        collectHomeData()
    }

    private fun collectHomeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.homeState.collect {
                if (!it.isLoading){
                    homeList.add(HomeItem(it.header, HomeItemType.TYPE_HEADER))
                    homeList.add(HomeItem(it.hourList, HomeItemType.TYPE_HOUR))
                    homeList.addAll(
                        it.dailyList.map { it.toHomeItem() },
                    )
                    homeList.add(HomeItem(it.details, HomeItemType.TYPE_DETAILS))
                    setAdapter()
                }
            }
        }
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter(homeList)
        binding.rvHome.adapter = homeAdapter
    }
}