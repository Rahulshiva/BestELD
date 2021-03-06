package com.eld.besteld.roomDataBase

import androidx.lifecycle.LiveData
import java.util.*

class DriverReposetry(private val driverDao: insertDriverInformationDao) {


    val allInfromation: LiveData<List<DayData>> = driverDao.getDayData()

    fun insertDayData(dayData:DayData) {
        driverDao.insertDayData(dayData)
    }

    fun insertDriverIno(driverInformation:DriverInformation) {
        driverDao.insertDriverInformation(driverInformation)
    }

    //TODO: Add a method to save day data
    fun insertDayDataForDayMetaData(dayData:DayData, dayMetaData: Date, driverId: String) {
        //if meta data is avaiable for that day

    }
}