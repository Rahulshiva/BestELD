package com.eld.besteld.roomDataBase

import androidx.room.Embedded
import androidx.room.Relation

data class DayMetaDataWithDayDataList (
    @Embedded
    val user: DayMetaData,
    @Relation(
        parentColumn = "day",
        entityColumn = "day"
    )
    val dayDataList: List<DayData>
)