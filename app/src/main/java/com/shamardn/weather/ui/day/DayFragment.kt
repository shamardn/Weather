package com.shamardn.weather.ui.day

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shamardn.weather.R
import com.shamardn.weather.databinding.FragmentDayBinding
import com.shamardn.weather.ui.base.BaseFragment
import com.shamardn.weather.util.toDayItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DayFragment : BaseFragment<FragmentDayBinding>() {
    override val layoutIdFragment = R.layout.fragment_day
    override val viewModel: DayViewModel by viewModels()
    lateinit var dayAdapter: DayAdapter
    val dayList: MutableList<DayItem<Any>> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        collectDayData()
    }

    private fun collectDayData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dayState.collectLatest {
                dayList.addAll(
                    it.dailyList.map { it.toDayItem() }
                )
                setAdapter()
            }
        }
    }

    private fun setAdapter() {
        dayAdapter = DayAdapter(dayList)
        binding.rvDaily.adapter = dayAdapter
    }
}