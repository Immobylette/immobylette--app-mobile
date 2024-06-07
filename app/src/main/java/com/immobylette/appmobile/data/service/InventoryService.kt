package com.immobylette.appmobile.data.service

import com.immobylette.appmobile.data.dto.AgentDto
import com.immobylette.appmobile.data.dto.ElementDto
import com.immobylette.appmobile.data.dto.ElementSummaryDto
import com.immobylette.appmobile.data.dto.InventorySummaryDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.UUID

interface InventoryService {
    @POST("properties/{id}/start")
    suspend fun startInventory(@Path("id") id: UUID, @Body agent: AgentDto): Response<UUID>

    @GET("inventories/{id}/walls")
    suspend fun getWalls(@Path("id") id: UUID): Response<List<ElementSummaryDto>>

    @GET("inventories/{id}/elements")
    suspend fun getElements(@Path("id") id: UUID): Response<List<ElementSummaryDto>>

    @GET("inventories/{inventoryId}/elements/{elementId}")
    suspend fun getElement(
        @Path("inventoryId") inventoryId: UUID,
        @Path("elementId") elementId: UUID
    ): Response<ElementDto>

    @Multipart
    @POST("inventories/{inventoryId}/elements/{elementId}")
    suspend fun postElementStep(
        @Path("inventoryId") inventoryId: UUID,
        @Path("elementId") elementId: UUID,
        @Part("step") step: RequestBody,
        @Part photos: List<MultipartBody.Part>,
    ): Response<Unit>

    @GET("/inventories/{id}/summary")
    suspend fun getSummary(@Path("id") id: UUID): Response<InventorySummaryDto>
}