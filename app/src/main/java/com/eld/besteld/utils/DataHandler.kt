package com.eld.besteld.utils

import com.eld.besteld.roomDataBase.DriverInformation
import java.sql.Driver

internal object DataHandler {
    private var instance: DataHandler? = null

    lateinit var currentDriver: DriverInformation

    init {
        println("Singleton class invoked.")
    }
}