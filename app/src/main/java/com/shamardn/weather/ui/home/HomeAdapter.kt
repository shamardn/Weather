package com.shamardn.weather.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.shamardn.weather.BR
import com.shamardn.weather.R
import com.shamardn.weather.ui.DaysAdapter
import com.shamardn.weather.ui.HoursAdapter
import com.shamardn.weather.ui.base.BaseAdapter
import com.shamardn.weather.ui.uistate.DailyWeatherUiState
import com.shamardn.weather.ui.uistate.HourlyWeatherUiState

class HomeAdapter(private var homeItems: MutableList<HomeItem>) : BaseAdapter<HomeItem>(homeItems) {
    override val layoutID = 0

    fun setItem(item: HomeItem) {
        val newItems = homeItems.apply {
            removeAt(item.priority)
            add(item.priority, item)
        }
        setItems(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (homeItems.isNotEmpty())
            bind(holder as ItemViewHolder, position)
    }

    override fun bind(holder: ItemViewHolder, position: Int) {
        if (position != -1)
            when (val currentItem = homeItems[position]) {
                is HomeItem.DailyWeather -> bindWeatherDays(holder, currentItem.items)
                is HomeItem.HourlyWeather -> bindWeatherHours(holder, currentItem.items)
            }
    }

    private fun bindWeatherDays(holder: ItemViewHolder, items: List<DailyWeatherUiState>) {
        holder.binding.run {
            setVariable(
                BR.adapterRecycler, DaysAdapter(items)
            )
        }
    }

    private fun bindWeatherHours(holder: ItemViewHolder, items: List<HourlyWeatherUiState>) {
        holder.binding.run {
            setVariable(
                BR.adapterRecycler, HoursAdapter(items)
            )
        }
    }

    override fun setItems(newItems: List<HomeItem>) {
        homeItems = newItems.sortedBy { it.priority }.toMutableList()
        super.setItems(homeItems)
    }

    override fun areItemsSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun areContentSame(
        oldPosition: HomeItem,
        newPosition: HomeItem,
    ): Boolean {
        return oldPosition == newPosition
    }

    override fun getItemViewType(position: Int): Int {
        if (homeItems.isNotEmpty()) {
            return when (homeItems[position]) {
                is HomeItem.DailyWeather -> R.layout.daily_item
                is HomeItem.HourlyWeather -> R.layout.hourly_item
            }
        }
        return -1
    }
}