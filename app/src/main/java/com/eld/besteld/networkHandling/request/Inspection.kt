package com.eld.besteld.networkHandling.request

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Inspection1 (

    @SerializedName("notes")
    private var notes: String? = null,

    @SerializedName("location")
    private var location: String? = null,

    @SerializedName("latitude")
    private var latitude: String? = null,

    @SerializedName("longitude")
    private var longitude: String? = null,

    @SerializedName("accepted")
    private var accepted: Boolean? = null

) : Parcelable