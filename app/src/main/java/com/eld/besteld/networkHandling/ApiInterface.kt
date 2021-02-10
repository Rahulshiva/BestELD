package com.eld.besteld.networkHandling
import com.eld.besteld.networkHandling.request.LoginRequest
import com.eld.besteld.networkHandling.responce.LoginResponce
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun addUser(@Body userData: LoginRequest): Call<LoginResponce>

}