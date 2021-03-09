package com.eld.besteld.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.eld.besteld.R
import java.util.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eld.besteld.dialogs.DutyHourDialog
import com.eld.besteld.dialogs.EldListDialog
import com.eld.besteld.dialogs.SelectDateDialogFragment
import com.eld.besteld.fragment.DutyInspectionFragment
import com.eld.besteld.fragment.GraphFragment
import com.eld.besteld.listener.EldDialogCallBack
import com.eld.besteld.roomDataBase.DayMetaData
import com.eld.besteld.roomDataBase.DriverViewModel
import com.eld.besteld.roomDataBase.LogDataViewModel
import com.eld.besteld.utils.DataHandler
import com.iosix.eldblelib.EldBleConnectionStateChangeCallback
import com.iosix.eldblelib.EldBleDataCallback
import com.iosix.eldblelib.EldBleError
import com.iosix.eldblelib.EldBleScanCallback
import com.iosix.eldblelib.EldBroadcast
import com.iosix.eldblelib.EldBroadcastTypes
import com.iosix.eldblelib.EldBufferRecord
import com.iosix.eldblelib.EldCachedNewTimeRecord
import com.iosix.eldblelib.EldCachedNewVinRecord
import com.iosix.eldblelib.EldCachedPeriodicRecord
import com.iosix.eldblelib.EldDriverBehaviorRecord
import com.iosix.eldblelib.EldFuelRecord
import com.iosix.eldblelib.EldManager
import com.iosix.eldblelib.EldScanObject
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progressbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity(R.layout.activity_main), View.OnClickListener,
    EldDialogCallBack {
    var MAC: String? = null
    private lateinit var context: Context
    private var mEldManager: EldManager? = null
    private var exit = false
    var reqdelinprogress = false
    var startseq = 0
    var endseq = 0
    var reccount = 0
    private val eldDeviceList = mutableListOf<EldScanObject>()

    lateinit var dataViewModel: LogDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        //Required to allow bluetooth scanning
        checkPermissionble()

        dataViewModel = ViewModelProvider(this).get(LogDataViewModel::class.java)

        mEldManager = EldManager.GetEldManager(this, "123456789A")
        runOnUiThread {
        }
        init()

       // enterInitailEntery()
    }

    private fun enterInitailEntery() {
        var dayDataObj = DayMetaData("123456","34434","xyz", "DJHDS324234")//DataHandler.currentDriver.dlNumber
        dataViewModel.insertDayMetaData(dayDataObj)
    }

    private fun checkPermissionble() {
        requestPermissions(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
                //working here
            ), 1
        )
    }

    private fun init() {
        showDutyFragment()
        showGraphFragment()
        settingClick()
        commitingFragments()
    }

    private fun showGraphFragment() {
        val graphFragment = GraphFragment()
        supportFragmentManager.beginTransaction().replace(R.id.containerGraph, graphFragment)
            .commit()
    }

    private fun showDutyFragment() {
        val DutyInspectionFragment = DutyInspectionFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.listContainer, DutyInspectionFragment).commit()
    }

    private fun commitingFragments() {

    }

    private fun settingClick() {
        bloothScanner.setOnClickListener(this)
        ivDuty.setOnClickListener(this)
        ivUserPic.setOnClickListener(this)
        ivDown.setOnClickListener(this)
    }

    override fun onBackPressed() {
        if (exit) {
            mEldManager!!.DisconnectEld()
            finish() // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show()
            exit = true
            Handler().postDelayed({ exit = false }, 3 * 1000.toLong())
        }
    }

    private val bleConnectionStateChangeCallback: EldBleConnectionStateChangeCallback =
        object : EldBleConnectionStateChangeCallback() {
            override fun onConnectionStateChange(newState: Int) {
                runOnUiThread {

                }
                if (newState == 0) {
                }
            }
        }


    private val bleDataCallback: EldBleDataCallback = object : EldBleDataCallback() {
        override fun OnDataRecord(
            dataRec: EldBroadcast,
            RecordType: EldBroadcastTypes
        ) {
            runOnUiThread {
                if (dataRec is EldBufferRecord) {
                    startseq = dataRec.startSeqNo
                    endseq = dataRec.endSeqNo
                } else if (RecordType == EldBroadcastTypes.ELD_DATA_RECORD) {
                } else if (RecordType == EldBroadcastTypes.ELD_CACHED_RECORD) {
                    if (reqdelinprogress) {
                        reccount++
                        Log.d("TESTING", "received $reccount records")
                        if (reccount == 10) {
                            Log.d(
                                "TESTING",
                                "delete " + startseq + "-" + (startseq + 9)
                            )
                            mEldManager!!.DeleteRecord(startseq, startseq + 9)
                            Log.d("TESTING", "request " + (startseq + 10))
                            mEldManager!!.RequestRecord(startseq + 10)
                        } else if (reccount == 11) {
                            Log.d("TESTING", "success!")
                            reqdelinprogress = false
                            reccount = 0
                        }
                    }
                    if (dataRec is EldCachedPeriodicRecord) {
                        Log.d(
                            "TESTING",
                            "Odometer " + dataRec.odometer
                        )
                        Log.d(
                            "TESTING",
                            "Engine Hours " + dataRec.engineHours
                        )
                        Log.d(
                            "TESTING",
                            "RPM " + dataRec.rpm
                        )
                        Log.d(
                            "TESTING",
                            "Satellites " + dataRec.numSats
                        )
                        Log.d(
                            "TESTING",
                            "Lat " + dataRec.latitude
                        )
                        Log.d(
                            "TESTING",
                            "Lon " + dataRec.longitude
                        )
                        Log.d(
                            "TESTING",
                            "Unix Time " + dataRec.unixTime
                        )
                        Log.d(
                            "TESTING",
                            "Sequence Number " + dataRec.seqNum
                        )

                        // mDataView.append("CACHED REC"+((EldCachedPeriodicRecord)(dataRec)).getBroadcastString());
                    } else if (dataRec is EldCachedNewTimeRecord) {
                        dataRec.engineHours
                        dataRec.newUnixTime
                    } else if (dataRec is EldCachedNewVinRecord) {
                        Log.d(
                            "TESTING",
                            "Vin " + dataRec.vin
                        )
                        Log.d(
                            "TESTING",
                            "Odometer " + dataRec.odometer
                        )
                        Log.d(
                            "TESTING",
                            "Engine Hours " + dataRec.engineHours
                        )
                        Log.d(
                            "TESTING",
                            "Unix Time " + dataRec.unixTime
                        )
                        Log.d(
                            "TESTING",
                            "Sequence Number " + dataRec.seqNum
                        )
                    }
                } else if (RecordType == EldBroadcastTypes.ELD_DRIVER_BEHAVIOR_RECORD) {
                    val rec = dataRec as EldDriverBehaviorRecord
                    if (rec is EldDriverBehaviorRecord) {
                        rec.absStatus

                    }
                } else if (RecordType == EldBroadcastTypes.ELD_ENGINE_PARAMETERS_RECORD) {


                } else if (RecordType == EldBroadcastTypes.ELD_EMISSIONS_PARAMETERS_RECORD) {


                } else if (RecordType == EldBroadcastTypes.ELD_TRANSMISSION_PARAMETERS_RECORD) {


                } else if (RecordType == EldBroadcastTypes.ELD_FUEL_RECORD) {
                    val rec = dataRec as EldFuelRecord
                }
            }
        }
    }


    private val bleScanCallback: EldBleScanCallback = object : EldBleScanCallback() {
        override fun onScanResult(device: EldScanObject) {
            Log.d("BLETEST", "BleScanCallback single")
            val strDevice: String
            if (device != null) {
                strDevice = device.deviceId
                runOnUiThread { }
                val res = mEldManager!!.ConnectToEld(
                    bleDataCallback,
                    EnumSet.of(
                        EldBroadcastTypes.ELD_BUFFER_RECORD,
                        EldBroadcastTypes.ELD_CACHED_RECORD,
                        EldBroadcastTypes.ELD_DATA_RECORD
                    ),
                    bleConnectionStateChangeCallback
                )
                if (res != EldBleError.SUCCESS) {
                    runOnUiThread { }
                }
            } else {
                runOnUiThread { }
            }
        }

        override fun onScanResult(deviceList: ArrayList<EldScanObject>?) {
            Log.d("BLETEST", "BleScanCallback multiple")
            val strDevice: String
            val so: EldScanObject
            if (deviceList != null) {
                progressbar.visibility = View.GONE
                so = deviceList[0]
                strDevice = so.deviceId
                MAC = strDevice
                eldDeviceList.addAll(deviceList)
                settingEldDeviceRecycler()
                runOnUiThread {


                }

                val res = mEldManager!!.ConnectToEld(
                    bleDataCallback,
                    EnumSet.of(
                        EldBroadcastTypes.ELD_BUFFER_RECORD,
                        EldBroadcastTypes.ELD_CACHED_RECORD,
                        EldBroadcastTypes.ELD_FUEL_RECORD,
                        EldBroadcastTypes.ELD_DATA_RECORD,
                        EldBroadcastTypes.ELD_DRIVER_BEHAVIOR_RECORD,
                        EldBroadcastTypes.ELD_EMISSIONS_PARAMETERS_RECORD,
                        EldBroadcastTypes.ELD_ENGINE_PARAMETERS_RECORD,
                        EldBroadcastTypes.ELD_TRANSMISSION_PARAMETERS_RECORD
                    ),
                    bleConnectionStateChangeCallback,
                    strDevice
                )
                if (res != EldBleError.SUCCESS) {
                    runOnUiThread {}
                }
            } else {
                runOnUiThread {
                    progressbar.visibility = View.GONE
                    Toast.makeText(context, "No device found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //setting eld recyler

    private fun settingEldDeviceRecycler() {
        val dialog = EldListDialog(this, eldDeviceList)
        dialog.setCallback(context)
        dialog.show(supportFragmentManager, "Home Activity")
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_BT_ENABLE) {
            // Make sure the request was successful

            //Working here
            if (resultCode == Activity.RESULT_OK) {
                mEldManager!!.ScanForElds(bleScanCallback)
            } else {
            }
        }
    }


    private fun ScanForEld() {
        if (mEldManager!!.ScanForElds(bleScanCallback) == EldBleError.BLUETOOTH_NOT_ENABLED) mEldManager!!.EnableBluetooth(
            REQUEST_BT_ENABLE
        )
    }

    companion object {
        private const val REQUEST_BASE = 100
        private const val REQUEST_BT_ENABLE = REQUEST_BASE + 1
    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.bloothScanner -> {
                progressbar.visibility = View.VISIBLE
                ScanForEld()
            }

            R.id.ivDuty -> {
                val dialog = DutyHourDialog()
                dialog.settingCallBack(context)
                dialog.show(supportFragmentManager, "Home Activity")
            }

            R.id.ivUserPic -> {

            }

            R.id.ivDown ->{

                val dialog = SelectDateDialogFragment()
                dialog.settingCallBack(context)
                dialog.show(supportFragmentManager,"Home ACtivity")

            }
        }


    }


    //eld bluttoth device set listener
    override fun onItemClick() {
        //TODO("Not yet implemented")
    }




}