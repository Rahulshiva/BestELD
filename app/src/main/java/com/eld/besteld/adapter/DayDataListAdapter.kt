package com.eld.besteld.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eld.besteld.R
import com.eld.besteld.models.DateData
import kotlinx.android.synthetic.main.day_meta_data_recycler.view.*
import java.util.*

class DayDataListAdapter(
    private val mContext: Context,
    var dateListData: List<DateData>,
    var dateSelectedFrom: String? = null

) : RecyclerView.Adapter<DayDataListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DayDataListAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.day_meta_data_recycler, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dateListData.size
    }

    override fun onBindViewHolder(holder: DayDataListAdapter.ViewHolder, position: Int) {

        if (dateListData.get(position).displayDate.equals(dateSelectedFrom)){
            holder.itemView.tvDaytoday.setText("Today")

        }else{
            holder.itemView.tvDaytoday.setText(dateListData.get(position).displayDate)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}