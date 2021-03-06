package com.eld.besteld.roomDataBase

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "DayMetaData")
data class DayMetaData(
    @SerializedName("day")
    val day: String?=null,

    @SerializedName("dayText")
    val dayText: String?=null,

    @PrimaryKey
    @SerializedName("dlNumber")
    val dlNumber: String

  /*  @PrimaryKey
@SerializedName("dlNumber")
val dlNumber: String*/
) : Parcelable