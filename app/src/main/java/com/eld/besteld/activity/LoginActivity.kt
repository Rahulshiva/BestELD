package com.eld.besteld.activity


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.room.PrimaryKey
import com.eld.besteld.R
import com.eld.besteld.dialogs.CommonDialogs
import com.eld.besteld.listener.DialogCallback
import com.eld.besteld.networkHandling.request.LoginRequest
import com.eld.besteld.networkHandling.responce.LoginResponce
import com.eld.besteld.roomDataBase.DriverInformation
import com.eld.besteld.roomDataBase.EldDataBaseExicution
import com.eld.besteld.roomDataBase.insertDriverInformationDao
import com.eld.besteld.utils.CommonUtils
import com.ethane.choosetobefit.web_services.RetrofitExecuter
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoginActivity : AppCompatActivity(R.layout.activity_login), View.OnClickListener, TextWatcher,
    DialogCallback {

    private lateinit var context: Context
    private lateinit var mUserInfo: LoginRequest
    private var driverInformation: insertDriverInformationDao? = null
    private lateinit var mDriverInformation : DriverInformation




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        driverInformation = EldDataBaseExicution.invoke(context).driverInformation

        init()
    }

    private fun init() {
        setListener()
    }

    private fun setListener() {
        btnLogin.setOnClickListener(this)
        ivDialogOption.setOnClickListener(this)
        etEmail.addTextChangedListener(this)
        etPassword.addTextChangedListener(this)
    }

    override fun onClick(view: View?) {


        when (view?.id) {

            R.id.btnLogin -> {

                if (checkValidation()) {

                    callLoginApi()

                }
            }

            R.id.ivDialogOption -> {

                val dialog = CommonDialogs(this)
                dialog.setCallback(context)
                dialog.show(supportFragmentManager, "Login Activity")





            }
        }

    }

    private fun callLoginApi() {

       progressbar.visibility = View.VISIBLE
        if (CommonUtils.isOnline(context)) {
            mUserInfo =
                LoginRequest(etEmail.text.toString(), etPassword.text.toString())

            val gson = GsonBuilder().disableHtmlEscaping().create()
            var call =
                RetrofitExecuter.getApiInterface("", "", "sdf", false).addUser(userData = mUserInfo)
            Log.e("url", "" + call.request().url)
            call.enqueue(object : Callback<LoginResponce?> {
                override fun onResponse(
                    call: Call<LoginResponce?>,
                    response: Response<LoginResponce?>
                ) {
                 progressbar.visibility = View.GONE
                    if (response.body() != null) {
                        progressbar.visibility = View.GONE

                        //saving driver information to the database from server
                        val mDriverInformation = DriverInformation(
                            mDriverInformation.city,
                            mDriverInformation.country,
                            mDriverInformation.dlBackPic,
                            mDriverInformation.dlExpiryDate,
                            mDriverInformation.dlFrontPiv,
                            mDriverInformation.dlNumber,
                            mDriverInformation.email,
                            mDriverInformation.firstName,
                            mDriverInformation.lastName,
                            mDriverInformation.country,
                            mDriverInformation.FleetDotNuber,
                            mDriverInformation.strAddress1,
                            mDriverInformation.strAddress2,
                            mDriverInformation.state,
                            mDriverInformation.zip,
                            mDriverInformation.primaryPhone
                        )

                      driverInformation?.insertCourseDetailData(mDriverInformation)


                        startActivity(Intent(context,MainActivity::class.java))

                    } else {
                        val gson = GsonBuilder().create()
                        try {

                            val jObjError = JSONObject(response.errorBody()!!.string())
                            Toast.makeText(
                                context,
                                jObjError.getString("message").toString(),
                                Toast.LENGTH_LONG
                            ).show()


                        } catch (e: IOException) {
                        }
                    }
                }

                override fun onFailure(
                    call: Call<LoginResponce?>,
                    t: Throwable
                ) {
                  progressbar.visibility = View.GONE
                    call.cancel()
                }
            })
        } else {
            Toast.makeText(context, "Please check internet connection", Toast.LENGTH_LONG)
        }

    }


    private fun checkValidation(): Boolean {

        if (etEmail.text.toString().isEmpty()) {
            tvEmailError.visibility = View.VISIBLE
            tvEmailError.text = getString(R.string.empty_email)
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
            tvEmailError.visibility = View.VISIBLE
            tvEmailError.text = getString(R.string.valid_email)
            return false
        } else if (etPassword.text.toString().isEmpty()) {
            tvPasswordError.visibility = View.VISIBLE
            tvPasswordError.text = "Please enter password."
            return false
        }
        return true
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        tvEmailError.visibility = View.GONE
        tvPasswordError.visibility = View.GONE
    }

    override fun onClose() {

    }

    override fun onDemo() {
        startActivity(Intent(context, MainActivity::class.java))
    }

    override fun onTest() {
        //TODO("Not yet implemented")
    }

    override fun onRadio() {
        //TODO("Not yet implemented")
    }

    override fun onHelp() {
        //TODO("Not yet implemented")
    }

    override fun onMessage() {
        //TODO("Not yet implemented")
    }

    override fun onDotInspect() {
        //TODO("Not yet implemented")
    }



    override fun onSetting() {
        //TODO("Not yet implemented")
    }

    override fun onLogBook() {
        //TODO("Not yet implemented")
    }

    override fun onSos() {
        //TODO("Not yet implemented")
    }
}