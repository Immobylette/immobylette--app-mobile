package com.immobylette.appmobile.utils

import com.immobylette.appmobile.data.service.PropertyService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.immobylette.appmobile.BuildConfig

object RetrofitHelper {

    private val retrofitClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_URL)
        .build()

    val propertyService: PropertyService = retrofitClient.create(PropertyService::class.java)
}