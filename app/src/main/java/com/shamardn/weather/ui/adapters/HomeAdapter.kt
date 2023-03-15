package com.shamardn.weather.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamardn.weather.BR
import com.shamardn.weather.R
import com.shamardn.weather.databinding.DetailsItemBinding
import com.shamardn.weather.databinding.HomeHeaderBinding
import com.shamardn.weather.databinding.ListDaysBinding
import com.shamardn.weather.databinding.ListHoursBinding
import com.shamardn.weather.ui.home.HomeItem
import com.shamardn.weather.ui.home.HomeItemType
import com.shamardn.weather.ui.home.HomeViewModel
import com.shamardn.weather.ui.home.uistate.CurrentWeatherUiState
import com.shamardn.weather.ui.home.uistate.DailyWeatherUiState
import com.shamardn.weather.ui.home.uistate.HourlyWeatherUiState

class HomeAdapter(private val items: List<HomeItem<Any>>, private val homeViewModel: HomeViewModel) :
    RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                HeaderViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.home_header, parent, false)
                )
            }
            VIEW_TYPE_DAY -> {
                DaysViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_days, parent, false)
                )
            }
            VIEW_TYPE_HOURS -> {
                HoursViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_hours, parent, false)
                )
            }
            VIEW_TYPE_DETAILS -> {
                DetailsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.details_item, parent, false)
                )
            }
            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is DaysViewHolder -> {
                bindDays(holder, position)
            }
            is HeaderViewHolder -> {
                bindHeader(holder, position)
            }
            is HoursViewHolder -> {
                bindHours(holder, position)
            }
            is DetailsViewHolder -> {
                bindDetails(holder, position)
            }
        }
    }

    private fun bindHeader(holder: HeaderViewHolder, position: Int) {
        val header = items[position].item as CurrentWeatherUiState
        holder.binding.setVariable(BR.item, header)
        holder.binding.setVariable(BR.viewModel, homeViewModel)
    }

    private fun bindDays(holder: DaysViewHolder, position: Int) {
        val days = items[position].item as List<DailyWeatherUiState>
        val daysAdapter = DaysAdapter(days)
        holder.binding.setVariable(BR.adapterRecycler, daysAdapter)
    }

    private fun bindHours(holder: HoursViewHolder, position: Int) {
        val hours = items[position].item as List<HourlyWeatherUiState>
        val hoursAdapter = HoursAdapter(hours)
        holder.binding.setVariable(BR.adapterRecycler, hoursAdapter)
    }

    private fun bindDetails(holder: DetailsViewHolder, position: Int) {
        val currentDayDetails = items[position].item as CurrentWeatherUiState
        holder.binding.setVariable(BR.item, currentDayDetails)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            HomeItemType.TYPE_DAY -> VIEW_TYPE_DAY
            HomeItemType.TYPE_HEADER -> VIEW_TYPE_HEADER
            HomeItemType.TYPE_HOUR -> VIEW_TYPE_HOURS
            HomeItemType.TYPE_DETAILS -> VIEW_TYPE_DETAILS
        }
    }

    override fun getItemCount() = items.size

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class HeaderViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val binding = HomeHeaderBinding.bind(itemView)
    }

    class DaysViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val binding = ListDaysBinding.bind(itemView)
    }

    class HoursViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val binding = ListHoursBinding.bind(itemView)
    }

    class DetailsViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val binding = DetailsItemBinding.bind(itemView)
    }

    companion object {
        private const val VIEW_TYPE_DAY = 1
        private const val VIEW_TYPE_HEADER = 2
        private const val VIEW_TYPE_HOURS = 3
        private const val VIEW_TYPE_DETAILS = 4
    }
}
