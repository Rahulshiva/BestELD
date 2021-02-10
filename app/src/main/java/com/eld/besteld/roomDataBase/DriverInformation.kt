package com.eld.besteld.roomDataBase

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DriverInformation(

    @SerializedName("city")
    val city: String?=null,

    @SerializedName("country")
    val country: String?=null,

    @SerializedName("dlBackPic")
    val dlBackPic: String?=null,

    @SerializedName("dlExpiryDate")
    val dlExpiryDate: String?=null,

    @SerializedName("dlFrontPiv")
    val dlFrontPiv: String,

    @PrimaryKey @NonNull
    @SerializedName ("dlNumber")
    val dlNumber: String,

    @SerializedName("email")
    val email: String?=null,

    @SerializedName("firstName")
    val firstName: String?=null,

    @SerializedName("FleetDotNuber")
    val FleetDotNuber: String?=null ,

    @SerializedName("lastName")
    val lastName: String?=null,

    @SerializedName("primaryPhone")
    val primaryPhone: String?=null,

    @SerializedName("secondaryPhone")
    val secondaryPhone: String?=null,

    @SerializedName("state")
    val state: String?=null,

    @SerializedName("strAddress1")
    val strAddress1: String?=null,

    @SerializedName("strAddress2")
    val strAddress2: String?=null,

    @SerializedName("zip")
    val zip: String?=null
) : Parcelable