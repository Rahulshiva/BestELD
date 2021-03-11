package com.eld.besteld.roomDataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class LogDataViewModel(application: Application) : AndroidViewModel(application) {

    private val logReposetry: LogDataRepository

    init{
        val dayDao = EldDataBaseExicution.invoke(application)?.dayDataDao()
        val dayMetaDao = EldDataBaseExicution.invoke(application)?.dayMetaDataDao()
        logReposetry = LogDataRepository(dayDataDao = dayDao!!,dayMetaDataDao = dayMetaDao!!)
    }

    fun insertDayDataForDayMetaData(dayData:DayData, dayMetaData: Date, dlNumber: String)=viewModelScope.launch (Dispatchers.IO){
        logReposetry.insertDayDataForDayMetaData(dayData, dayMetaData, dlNumber)
    }

    fun insertDayMetaData(metaData: DayMetaData)=viewModelScope.launch (Dispatchers.IO){    //I add Dispatchers.IO
        logReposetry.insertDayMetaData(metaData)
    }

/*    fun insertDayMetaData(metaData: DayMetaData) {
        logReposetry.insertDayMetaData(metaData)
    }*/

}