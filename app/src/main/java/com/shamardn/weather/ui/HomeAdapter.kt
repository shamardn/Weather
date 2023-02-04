package com.shamardn.weather.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamardn.weather.R
import com.shamardn.weather.databinding.DayItemBinding
import com.shamardn.weather.databinding.DetailsItemBinding
import com.shamardn.weather.databinding.HeaderItemBinding
import com.shamardn.weather.databinding.HourlyItemBinding
import com.shamardn.weather.ui.uistate.HourlyWeatherUiState

class HomeAdapter(private var list: List<HourlyWeatherUiState>): RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType) {
            VIEW_TYPE_HOUR -> {
                 HourViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hourly_item, parent, false))
            }
            else -> {
                 HourViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hourly_item, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentHour = list[position]
        when(holder) {
            is HeaderViewHolder ->{
                //TODO
            }
            is HourViewHolder ->{
                holder.binding.apply {
                    textHour.text = currentHour.date.toString()
                    // image
                    textTempHour.text = currentHour.temp.toString()
                }
            }
            is DayViewHolder ->{
                //TODO
            }
            is DetailsViewHolder ->{
                //TODO
            }
        }

    }

    override fun getItemCount() = list.size

    fun setData(newList: List<HourlyWeatherUiState>){
        list = newList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        list[position]
        return super.getItemViewType(position)
    }

    abstract class BaseViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem)

    class HeaderViewHolder(viewItem: View): BaseViewHolder(viewItem){
        val binding = HeaderItemBinding.bind(viewItem)
    }
    class HourViewHolder(viewItem: View): BaseViewHolder(viewItem){
        val binding = HourlyItemBinding.bind(viewItem)
    }
    class DayViewHolder(viewItem: View): BaseViewHolder(viewItem){
        val binding = DayItemBinding.bind(viewItem)
    }
    class DetailsViewHolder(viewItem: View): BaseViewHolder(viewItem){
        val binding = DetailsItemBinding.bind(viewItem)
    }

    companion object{
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_HOUR = 2
        const val VIEW_TYPE_DAY = 3
        const val VIEW_TYPE_DETAILS = 4
    }
}