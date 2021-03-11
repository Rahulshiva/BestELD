package com.eld.besteld.roomDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DayDataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDayData(dayData:DayData)

    @Query("select * from DayData")
    fun getDayData(): LiveData<List<DayData>>

}