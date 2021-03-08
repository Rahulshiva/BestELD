package com.eld.besteld.networkHandling.request

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DutyDataRequest(
    @SerializedName("date")
    @Expose
    private var date: Long = 0,

    @SerializedName("dayData")
    @Expose
    private var dayData: List<DayDatum?>? = null

//    @SerializedName("inspection")
//    @Expose
//    private var inspection: List<Inspection?>? =
//        null

) : Parcelable
