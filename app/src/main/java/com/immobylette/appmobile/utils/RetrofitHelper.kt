package com.immobylette.appmobile.utils

import com.immobylette.appmobile.BuildConfig
import com.immobylette.appmobile.data.service.AgentService
import com.immobylette.appmobile.data.service.InventoryService
import com.immobylette.appmobile.data.service.PropertyService
import com.immobylette.appmobile.data.service.RoomService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHelper {
    private val headerInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", BuildConfig.API_KEY)
            .build()
        chain.proceed(request)
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofitClient: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .build()

    val propertyService: PropertyService = retrofitClient.create(PropertyService::class.java)
    val agentService: AgentService = retrofitClient.create(AgentService::class.java)
    val inventoryService: InventoryService = retrofitClient.create(InventoryService::class.java)
    val roomService: RoomService = retrofitClient.create(RoomService::class.java)
}