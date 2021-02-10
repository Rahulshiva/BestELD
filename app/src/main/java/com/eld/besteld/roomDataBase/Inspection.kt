package com.eld.besteld.roomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Inspection")
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
)