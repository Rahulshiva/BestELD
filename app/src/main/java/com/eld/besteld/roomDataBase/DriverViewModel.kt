package com.eld.besteld.roomDataBase

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DriverViewModel(application: Application) : AndroidViewModel(application) {

    private val noteReposetry:DriverReposetry
    val dayDaya : LiveData<List<DayData>>

    init{
        val dao = EldDataBaseExicution.invoke(application).getDriverDao()
        noteReposetry = DriverReposetry(dao)
        dayDaya = noteReposetry.allInfromation
    }

    fun insertDayData(dayData:DayData) = viewModelScope.launch( Dispatchers.IO ) {
        noteReposetry.insertDayData(dayData)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertDayDataForDayMetaData(dayData:DayData, dayMetaData: Date, dlNumber: String) {
        noteReposetry.insertDayDataForDayMetaData(dayData, dayMetaData, dlNumber)
    }

    fun insertDriverInfromation(driverInformation: DriverInformation) = viewModelScope.launch (Dispatchers.IO){
        noteReposetry.insertDriverIno(driverInformation)
    }
}