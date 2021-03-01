package com.eld.besteld.dialogs

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.aagito.imageradiobutton.RadioImageGroup
import com.eld.besteld.R
import com.eld.besteld.roomDataBase.*
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.duty_inspection_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DutyHourDialog() : DialogFragment() {

    private lateinit var mContext: Context
    private var day = ""
    private var dutyStatus = ""
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var PERMISSION_ID = 1000
    private val offDuty = 2131231065
    private val onDuty = 2131231066
    private val sleeper = 2131231067
    private var startTime = "00:00"
    private var endTime = "00:00"
    private var temp = "00:00"
    private var startLatitude:Double = 0.0
    private var startLongitude:Double = 0.0
    private var id = ""
    private lateinit var dayData:DayData
    private var dayDataList : insertDriverInformationDao?= null
    private lateinit var viewModel: DriverViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.duty_inspection_layout, container, false)
    }
    override fun onStart() {
        super.onStart()
        setListener()

        etStartTime.setText(startTime)
        etEndTime.setText(endTime)
        settingCallBack(mContext)
        viewModel = ViewModelProvider(this).get(DriverViewModel::class.java)
        dayDataList = EldDataBaseExicution.invoke(mContext).getDriverDao()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext)
    }

    fun settingCallBack(context: Context) {
        this.mContext = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(Objects.requireNonNull(mContext))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.attributes?.windowAnimations = R.style.dialogAnimation
        dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.let {
            val layoutParams: WindowManager.LayoutParams = it.attributes
            it.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            layoutParams.dimAmount = 0.0f
            layoutParams.gravity = Gravity.BOTTOM
            // it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.attributes = layoutParams
        }
        return dialog
    }
    private fun setListener() {
        btnConfirm.setOnClickListener(this::onClick)
        btnCancel.setOnClickListener(this::onClick)
        etLocation.setOnClickListener(this::onClick)
        rbOffDuty.setOnClickListener(this::onClick)
        rbOnDuty.setOnClickListener(this::onClick)
        rbSleeper.setOnClickListener(this::onClick)
       getCurruntTime()
        radioGroupDriverStatus.setOnCheckedChangeListener(object :
            RadioImageGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(
                radioGroup: View,
                radioButton: View?,
                isChecked: Boolean,
                checkedId: Int
            ) {

                if (onDuty == checkedId) {
                    dutyStatus = "onDuty"
                } else if (offDuty == checkedId) {
                    dutyStatus = "offDuty"
                } else if (sleeper == checkedId) {
                    dutyStatus = "sleeper"
                }
            }

        })
    }

    private fun getCurruntTime():String {
        val sdf = SimpleDateFormat("yyyy.MM.dd hh:mm:ss")
        val currentDate = sdf.format(Date())
        currentDate.get(1)
        startTime = currentDate.get(0).toString()
        return startTime
    }

    fun onClick(view: View) {
        when (view) {
            btnCancel -> dismiss()
            //  btnConfirm -> listener.onButtonConfirm(rbEnableYardMode.id,et)
            btnConfirm -> {
                dayData=(DayData(id = "233",startLatitude = startLatitude,startLongitude = startLongitude,rideDesciption = etNotes.text.toString(), startTime = startTime,endTime = endTime,autoID = 0,day = day))
                viewModel?.insertDayData(dayData)

             //   listener.onDismissClicked(day, etNotes.text.toString(),dutyStatus,startTime,endTime)
                dismiss()
            }
            etLocation -> {

                if (RequestPermission()) {

                    getLastLocation()
                }
            }
            rbOffDuty -> {
                startTime = getCurruntTime()
                swapStartTimeEndTime(startTime)
            }

            rbOnDuty -> {
                startTime = getCurruntTime()
                swapStartTimeEndTime(startTime)
            }

            rbSleeper -> {
                startTime = getCurruntTime()
                swapStartTimeEndTime(startTime)
            }
        }
    }

    private fun usingBackgroundThread() {
        CoroutineScope(Dispatchers.IO).launch {
         //   dayDataList?.insertDayData(dayData)

        }    }

    private fun swapStartTimeEndTime(startTime: String) {
       temp = startTime
        this.startTime = endTime
        endTime = temp
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            mContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    mContext as Activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

    }
    //check location is enabled or not
    fun isLocationEnabled(): Boolean {
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        val locationManager =
            mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug:", "You have the Permission")
            }
        }
    }
    private fun getCityName(lat: Double, long: Double): String {
        var geoCoder = Geocoder(mContext, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat, long, 3)
        return Adress.get(1).getAddressLine(0);

    }

    fun getLastLocation() {
        if (checkPermission()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        NewLocationData()
                    } else {
                        day = getCityName(location.latitude, location.longitude)
                        etLocation.setText(day)
                    }
                }
            } else {
                Toast.makeText(mContext, "Please Turn on Your device Location", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            RequestPermission()
        }
    }


    fun RequestPermission(): Boolean {
        //this function will allows us to tell the user to requesut the necessary permsiion if they are not garented
        ActivityCompat.requestPermissions(
            mContext as Activity,
            arrayOf(
              Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
        return true
    }
    //fuction to allow user permission


    fun NewLocationData() {
        var locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext)
        if (ActivityCompat.checkSelfPermission(
                mContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:", "your last last location: " + lastLocation.longitude.toString())
            startLatitude = lastLocation.latitude
            startLongitude = lastLocation.longitude
            day = "" + getCityName(lastLocation.latitude, lastLocation.longitude)
            etLocation.setText("" + getCityName(lastLocation.latitude, lastLocation.longitude))
        }
    }
}







