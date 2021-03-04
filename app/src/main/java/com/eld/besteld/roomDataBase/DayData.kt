package com.eld.besteld.roomDataBase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DayData(
    val day: String = "",

    val dlNumber: String = "",

    val dutyStatus: String = "",

    val endLatitude: String = "",

    val endLocation: String = "",

    val endLongitude: String = "",

    val endOdometer: String = "",

    val endTime: Long,

    val endTimeString: String = "",

    @PrimaryKey(autoGenerate = true)
    val autoID: Int,

    val id: String,

    val isVoilation: Boolean? = false,

    val rideDesciption: String = "",

    val startLatitude: Double = 0.0,

    val startLocation: String = "",

    val startLongitude: Double = 0.0,

    val startOdometer: String = "",

    val startTime: Long,

    val startTimeString: String = "",
    val date: String = ""
) : Parcelable