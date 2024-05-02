package com.immobylette.appmobile.data.service

import com.immobylette.appmobile.data.dto.PaginatedPropertiesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

import com.immobylette.appmobile.data.dto.PropertyDto
import retrofit2.http.Query

interface PropertyService {
    @GET("properties")
    suspend fun getProperties(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double
    ): Response<PaginatedPropertiesDto>

    @GET("properties/{id}")
    suspend fun getProperty(@Path("id") id: UUID): Response<PropertyDto>
}