package com.eld.besteld.roomDataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface insertDriverInformationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCourseDetailData(driverInformation: DriverInformation)
}