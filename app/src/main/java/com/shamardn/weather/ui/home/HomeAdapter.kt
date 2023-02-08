package com.shamardn.weather.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamardn.weather.BR
import com.shamardn.weather.R
import com.shamardn.weather.databinding.DailyItemBinding
import com.shamardn.weather.databinding.HomeHeaderBinding
import com.shamardn.weather.ui.uistate.CurrentWeatherUiState
import com.shamardn.weather.ui.uistate.DailyWeatherUiState

class HomeAdapter(private val items: List<HomeItem<Any>>) :
    RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_DAY -> {
                DayViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.daily_item, parent, false))
            }
            VIEW_TYPE_HEADER -> {
                HeaderViewHolder(
                    LayoutInflater.from(parent.context)
                    .inflate(R.layout.home_header, parent, false))
            }
//            VIEW_TYPE_STORIES -> {
//                StoriesViewHolder(
//                    LayoutInflater.from(parent.context)
//                    .inflate(R.layout.layout_stories, parent, false))
//            }
            else -> super.createViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is DayViewHolder -> {
                bindDay(holder, position)
            }
            is HeaderViewHolder -> {
                bindHeader(holder, position)
            }
//            is StoriesViewHolder -> {
//                bindStories(holder, position)
//            }
        }
    }

    private fun bindDay(holder: DayViewHolder, position: Int) {
        val currentDay = items[position].item as DailyWeatherUiState
        holder.binding.setVariable(BR.item, currentDay)
    }

//    private fun bindStories(holder: StoriesViewHolder, position: Int) {
//        val stories = items[position].item as List<Story>
//        val storiesAdapter = StoriesAdapter(stories)
//        holder.binding.apply {
//            rvStories.adapter = storiesAdapter
//        }
//    }

    private fun bindHeader(holder: HeaderViewHolder, position: Int) {
        val header = items[position].item as CurrentWeatherUiState
        holder.binding.setVariable(BR.item,header)
    }


    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            HomeItemType.TYPE_DAY -> VIEW_TYPE_DAY
            HomeItemType.TYPE_HEADER -> VIEW_TYPE_HEADER
        }
    }

    override fun getItemCount() = items.size

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DayViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val binding = DailyItemBinding.bind(itemView)
    }

    class HeaderViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val binding = HomeHeaderBinding.bind(itemView)
    }

//    class StoriesViewHolder(itemView: View) : BaseViewHolder(itemView) {
//        val binding = LayoutStoriesBinding.bind(itemView)
//    }

    companion object {
        private const val VIEW_TYPE_DAY = 1
        private const val VIEW_TYPE_HEADER = 2
    }
}
