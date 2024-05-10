package com.immobylette.appmobile.utils

import com.immobylette.appmobile.data.service.PropertyService
import com.immobylette.appmobile.data.service.AgentService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.immobylette.appmobile.BuildConfig
import com.immobylette.appmobile.data.service.InventoryService

object RetrofitHelper {

    private val retrofitClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_URL)
        .build()

    val propertyService: PropertyService = retrofitClient.create(PropertyService::class.java)
    val agentService: AgentService = retrofitClient.create(AgentService::class.java)
    val inventoryService: InventoryService = retrofitClient.create(InventoryService::class.java)
}