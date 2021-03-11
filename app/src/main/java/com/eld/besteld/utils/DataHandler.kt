package com.eld.besteld.utils

import com.eld.besteld.roomDataBase.DayData
import com.eld.besteld.roomDataBase.DriverInformation
import kotlinx.android.synthetic.main.duty_inspection_layout.*
import java.sql.Driver
import java.util.*

internal object DataHandler {
    private var instance: DataHandler? = null

    lateinit var currentDriver: DriverInformation
    lateinit var currentDayData: DayData

    init {
       // currentDriver = DataHandler.currentDriver
        println("Singleton class invoked.")
    }

    fun createDayData(start: Date, end: Date, status: DutyStatus, desciption: String?, driver: Driver): DayData? {
/*
        var dayDataObj = DayData(id = "233",
            startLatitude = startLatitude,
            startLongitude = startLongitude,
            rideDesciption = etNotes.text.toString(),
            startTime = startTime,
            endTime = endTime,
            autoID = 0,
            dutyStatus = dutyStatus,
            dlNumber = "1231231233",
            day = day
        )
*/

        return null
    }


/*


    func createDayData(start: Date, end: Date, status: DutyStatus, desciption: String?, for driver: Driver) -> DayData? {
        let context = BLDAppUtility.dataContext()
        let entity = NSEntityDescription.entity(forEntityName: "DayData", in: context)
        let dayMetaDataObj = NSManagedObject(entity: entity!, insertInto: context)

        dayMetaDataObj.setValue("string", forKey: "day")
        dayMetaDataObj.setValue(driver.dlNumber, forKey: "dlNumber")
        dayMetaDataObj.setValue(status.rawValue, forKey: "dutyStatus")
        dayMetaDataObj.setValue(end, forKey: "endTime")
        dayMetaDataObj.setValue(end.description, forKey: "endTimeString")
        // #warning generate Id
        dayMetaDataObj.setValue("1009", forKey: "id") //
        let currentLocationObj = BldLocationManager.shared.currentLocation
                dayMetaDataObj.setValue(currentLocationObj?.coordinate.latitude, forKey: "startLatitude")
        dayMetaDataObj.setValue(currentLocationObj?.coordinate.longitude, forKey: "startLongitude")
        dayMetaDataObj.setValue(desciption ?? "", forKey: "rideDescription")
        dayMetaDataObj.setValue(start, forKey: "startTime")
        dayMetaDataObj.setValue(start.description, forKey: "startTimeString")
        let userLocation = BldLocationManager.shared.locationText
                dayMetaDataObj.setValue(userLocation, forKey: "startLocation")

        let eldDataRecord = EldDeviceManager.shared.currentEldDataRecord
                if ((eldDataRecord != nil) && (status == DutyStatus.DRIVING || status == DutyStatus.ONDUTY)) {
                    dayMetaDataObj.setValue(eldDataRecord?.odometer ?? 0, forKey: "startOdometer")
                }

        let driverMetaData = dayMetaData(dayStart: BLDAppUtility.startOfTheDayTimeInterval(for: Date()), driverDL: driver.dlNumber ?? TEST_DRIVER_DL_NUMBER)
        if driverMetaData?.dayData?.count ?? 0 < 1 {
            driverMetaData?.setValue(NSSet(object: dayMetaDataObj), forKey: "DayData")
        } else {
            let dayDataArr = driverMetaData?.mutableSetValue(forKey: "DayData")
            dayDataArr?.add(dayMetaDataObj)
        }
        do {
            try context.save()
            } catch {
                print("Failed to save driver data\(error)")
            }

            return dayMetaDataObj as? DayData
        } */

}