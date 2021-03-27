package com.eld.besteld.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.eld.besteld.R
import com.eld.besteld.adapter.DayDataListAdapter
import com.eld.besteld.models.DateData
import kotlinx.android.synthetic.main.select_date_layout_dialog.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SelectDateDialogFragment : DialogFragment() {

    lateinit var mContext: Context
    lateinit var dayDataListAdapter: DayDataListAdapter
    lateinit var dateData: DateData
    private var dateListData = ArrayList<DateData>()

    var sdf = SimpleDateFormat("yyyy/MM/dd")
    val currentDate = sdf.format(Date())
    //split year, month and days from the date using StringBuffer.
    var sBuffer = StringBuffer(currentDate)
    var year = sBuffer.substring(2, 4)
    var mon = sBuffer.substring(5, 7)
    var dd = sBuffer.substring(8, 10)
    var modifiedFromDate = "$dd/$mon/$year"
    var MILLIS_IN_DAY = 1000 * 60 * 60 * 24

    var dateFormat = SimpleDateFormat("dd/MM/yy")
    var dateSelectedFrom: Date? = null
    var dateNextDate: Date? = null

    var formatted : String? = null
    var dayToday : String? = null
    var perviiousDay : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.select_date_layout_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        settingCallBack(mContext)
        try {
            dateSelectedFrom = dateFormat.parse(modifiedFromDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var nextDate = dateFormat.format(dateSelectedFrom!!.time + MILLIS_IN_DAY)
        var newDate = dateFormat.format(dateSelectedFrom!!.time + MILLIS_IN_DAY)
        formatted = nextDate.format(dateNextDate)
        dayToday = formatted
        for (i in 1..7) {
            nextDate = newDate
            dateNextDate = dateFormat.parse(nextDate)
             formatted = nextDate.format(dateNextDate)
            dateData = DateData(formatted!!,123.32,223.2)
            dateListData.add(dateData)
            newDate = dateFormat.format(dateNextDate!!.time - MILLIS_IN_DAY)
        }
        settingDayListAdapter()

    }

    private fun settingDayListAdapter() {

        rvdayRecycelr.layoutManager = LinearLayoutManager(mContext)
        val dayDataListAdapter = DayDataListAdapter(mContext, dateListData,dayToday)
        rvdayRecycelr.adapter = dayDataListAdapter
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =
            Dialog(Objects.requireNonNull(mContext))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.attributes?.windowAnimations = R.style.dialogAnimation
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.window?.let {
            val layoutParams: WindowManager.LayoutParams =
                it.attributes
            it.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            layoutParams.dimAmount = 0.0f
            layoutParams.gravity = Gravity.BOTTOM
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.attributes = layoutParams
        }
        return dialog
    }

    fun settingCallBack(context: Context) {
        this.mContext = context
    }
}