package com.eld.besteld.roomDataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface  DayMetaDataDao {

    @Query("select * from DayMetaData")
    fun getDayMetaData(): LiveData<List<DayMetaData>>

    @Transaction
    //@Query("SELECT * FROM DayMetaData where day = :inDay")
    @Query("SELECT * FROM DayMetaData")
    fun getUsersWithPlaylists(): List<DayMetaDataWithDayDataList>

    @Transaction
    //@Query("SELECT * FROM DayMetaData where day = :inDay")
    @Query("SELECT * FROM DayMetaData where day_meta = :dayTimeInterval")
    fun getCurrentDayMetaData(dayTimeInterval: Long): List<DayMetaDataWithDayDataList>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDayMetaData(metaDta: DayMetaData)

    @Query("select * from DayMetaData where day_meta = :dayTimeInterval AND dlNumber = :dlNumber")
    fun getDayMetaDataForDay(dayTimeInterval: Long, dlNumber: String): List<DayMetaData>

}