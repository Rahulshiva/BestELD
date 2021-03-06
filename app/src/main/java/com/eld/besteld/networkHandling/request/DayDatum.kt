package com.eld.besteld.networkHandling.request

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DayDatum (
    @SerializedName("dayDataId")
    @Expose
    private var dayDataId: Int? = null,

    @SerializedName("dlnumber")
    @Expose
    private var dlnumber: String? = null,

    @SerializedName("dutystatus")
    @Expose
    private var dutystatus: String? = null,

    @SerializedName("startlocation")
    @Expose
    private var startlocation: String? = null,

    @SerializedName("endlocation")
    @Expose
    private var endlocation: String? = null,

    @SerializedName("startlocationlatitude")
    @Expose
    private var startlocationlatitude: String? = null,

    @SerializedName("startlocationlongitude")
    @Expose
    private var startlocationlongitude: String? = null,

    @SerializedName("endlocationlatitude")
    @Expose
    private var endlocationlatitude: String? = null,

    @SerializedName("endlocationlongitude")
    @Expose
    private var endlocationlongitude: String? = null,

    @SerializedName("startodometer")
    @Expose
    private var startodometer: Int? = null,

    @SerializedName("starttime")
    @Expose
    private var starttime: String? = null,

    @SerializedName("endtime")
    @Expose
    private var endtime: String? = null,

    @SerializedName("endodometer")
    @Expose
    private var endodometer: Int? = null,

    @SerializedName("ridedescription")
    @Expose
    private var ridedescription: String? = null

): Parcelable