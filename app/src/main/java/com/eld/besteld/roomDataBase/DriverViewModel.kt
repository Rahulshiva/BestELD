package com.eld.besteld.roomDataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    fun insertDriverInfromation(driverInformation: DriverInformation) = viewModelScope.launch (Dispatchers.IO){
        noteReposetry.insertDriverIno(driverInformation)
    }
}