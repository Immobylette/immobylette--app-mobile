package com.immobylette.appmobile.utils

import com.immobylette.appmobile.data.service.PropertyService
import com.immobylette.appmobile.data.service.AgentService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.immobylette.appmobile.BuildConfig
import com.immobylette.appmobile.data.service.InventoryService
import com.immobylette.appmobile.data.service.RoomService
import okhttp3.Interceptor
import okhttp3.OkHttpClient

object RetrofitHelper {
    private val headerInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", BuildConfig.API_KEY)
            .build()
        chain.proceed(request)
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private val retrofitClient: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_URL)
        .build()

    val propertyService: PropertyService = retrofitClient.create(PropertyService::class.java)
    val agentService: AgentService = retrofitClient.create(AgentService::class.java)
    val inventoryService: InventoryService = retrofitClient.create(InventoryService::class.java)
    val roomService: RoomService = retrofitClient.create(RoomService::class.java)
}