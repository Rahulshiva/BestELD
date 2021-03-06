package com.eld.besteld.roomDataBase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "DayMetaData")
data class DayMetaData(
    @PrimaryKey
    @SerializedName("day")
    val day: String,

    @SerializedName("id")
    val id: String?=null,

    @SerializedName("driverId")
    val driverId: String,

    @SerializedName("dlNumber")
    val dlNumber: String,

    val dayDataArray: List<DayData>? = null,

    val inspectionArray: List<Inspection>? = null
  /*  @PrimaryKey
@SerializedName("dlNumber")
val dlNumber: String*/
) : Parcelable