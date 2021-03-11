package com.eld.besteld.roomDataBase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class Inspection(

    @PrimaryKey
    @SerializedName("")
    val latitude: Double,

    @SerializedName("")
    val location: String?=null,

    @SerializedName("")
    val longitude: Double?=null,

    @SerializedName("")
    val notes: String?=null,

    @SerializedName("")
    val type: String?=null
) : Parcelable