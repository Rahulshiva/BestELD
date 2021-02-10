package com.eld.besteld.roomDataBase
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DayData(
    @SerializedName("day")
    val day : String? = null,

    @SerializedName("id")
    val dlNumber : String?=null,

    @SerializedName("id")
    val dutyStatus : String?=null,

    @SerializedName("id")
    val endLatitude : String?=null,

    @SerializedName("id")
    val endLocation : String?=null,

    @SerializedName("id")
    val endLongitude : String?=null,

    @SerializedName("id")
    val endOdometer : String?=null,

    @SerializedName("id")
    val endTime : String?=null,

    @SerializedName("id")
    val endTimeString : String?=null,

    @PrimaryKey
    @SerializedName("id")
    val id : String,

    @SerializedName("id")
    val isVoilation : Boolean?=false,

    @SerializedName("id")
    val rideDesciption : String?=null,

    @SerializedName("id")
    val startLatitude : String?=null,

    @SerializedName("id")
    val startLocation : String?=null,

    @SerializedName("id")
    val startLongitude : Double?=null,

    @SerializedName("id")
    val startOdometer : String?=null,

    @SerializedName("id")
    val startTime : String?=null,

    @SerializedName("id")
    val startTimeString : String?=null
) : Parcelable