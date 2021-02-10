package com.ethane.choosetobefit.web_services

import com.eld.besteld.networkHandling.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitExecuter {
    fun getApiInterface(
        callingFor: String?,
        accessToken: String?,
        tokenType: String?,
        isFrom: Boolean
    ): ApiInterface {

      //  val BASE_URL = "13.233.111.74:5000"
        val BASE_URL = "http:52.53.153.62:8080/api/"
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                okHttpClient.newBuilder()
                    .connectTimeout(2 * 60 * 1000.toLong(), TimeUnit.SECONDS)
                    .readTimeout(2 * 60 * 1000.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(2 * 60 * 1000.toLong(), TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer" + " " + accessToken)
                            .method(original.method, original.body)
                        val request = requestBuilder.build()
                        chain.proceed(request)
                    }
                    .addInterceptor(interceptor)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(80, TimeUnit.SECONDS)
                    .build()
            ).build()
        return retrofit.create(ApiInterface::class.java)
    }
}