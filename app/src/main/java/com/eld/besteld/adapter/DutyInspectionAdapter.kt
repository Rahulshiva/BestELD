package com.eld.besteld.adapter

import  android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eld.besteld.R
import com.eld.besteld.roomDataBase.DayData
import kotlinx.android.synthetic.main.driver_information_recycler_layout.view.*
import kotlinx.android.synthetic.main.duty_inspection_layout.view.*
import kotlinx.android.synthetic.main.duty_inspection_layout.view.tvStartTime
import kotlinx.android.synthetic.main.odometer_reading_row_layout.view.*

class DutyInspectionAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<DutyInspectionAdapter.ViewHolder>() {


    val dayData = ArrayList<DayData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DutyInspectionAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.driver_information_recycler_layout, parent, false)
        )
    }

    fun UpdateList(newList: List<DayData>) {
        dayData.clear()
        dayData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dayData.size
    }

    override fun onBindViewHolder(holder: DutyInspectionAdapter.ViewHolder, position: Int) {

        holder.itemView.tvNotes.text = dayData.get(position).rideDesciption
        holder.itemView.tvLocation.text = dayData.get(position).day
        holder.itemView.tvStartTime?.text = dayData.get(position).startTime
        holder.itemView.tvEndtime?.text = dayData.get(position).endTime
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}