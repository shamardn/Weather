package com.shamardn.weather.ui.day

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamardn.weather.BR
import com.shamardn.weather.R
import com.shamardn.weather.databinding.DailyItemBinding
import com.shamardn.weather.ui.uistate.DailyWeatherUiState


class DayAdapter(private val items: List<DayItem<Any>>) :
    RecyclerView.Adapter<DayAdapter.BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_DAY -> {
                DayViewHolder(
                    LayoutInflater.from(parent.context)
                    .inflate(R.layout.daily_item, parent, false))
            }
//            VIEW_TYPE_STATUS -> {
//                StatusViewHolder(
//                    LayoutInflater.from(parent.context)
//                    .inflate(R.layout.item_status, parent, false))
//            }
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
//            is StatusViewHolder -> {
//                bindStatus(holder, position)
//            }
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

//    private fun bindStatus(holder: StatusViewHolder, position: Int) {
//        val status = items[position].item as String
//        holder.binding.apply {
//            etStatus.hint = status
//        }
//    }


    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            DayItemType.TYPE_DAY -> VIEW_TYPE_DAY
        }
    }

    override fun getItemCount() = items.size

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class DayViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val binding = DailyItemBinding.bind(itemView)
    }

//    class StatusViewHolder(itemView: View) : BaseViewHolder(itemView) {
//        val binding = ItemStatusBinding.bind(itemView)
//    }
//
//    class StoriesViewHolder(itemView: View) : BaseViewHolder(itemView) {
//        val binding = LayoutStoriesBinding.bind(itemView)
//    }

    companion object {
        private const val VIEW_TYPE_DAY = 1
    }
}
