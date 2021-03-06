package com.eld.besteld.roomDataBase

import androidx.lifecycle.LiveData

class DriverReposetry(private val driverDao: insertDriverInformationDao) {


    val allInfromation: LiveData<List<DayData>> = driverDao.getDayData()

    fun insertDayData(dayData:DayData) {
        driverDao.insertDayData(dayData)
    }

    fun insertDriverIno(driverInformation:DriverInformation) {
        driverDao.insertDriverInformation(driverInformation)
    }


}