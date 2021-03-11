package com.eld.besteld.utils

import com.eld.besteld.roomDataBase.DayData
import com.eld.besteld.roomDataBase.DriverInformation
import java.sql.Driver

internal object DataHandler {
    private var instance: DataHandler? = null

    lateinit var currentDriver: DriverInformation
    lateinit var currentDayData: DayData

    init {
       // currentDriver = DataHandler.currentDriver
        println("Singleton class invoked.")
    }
}