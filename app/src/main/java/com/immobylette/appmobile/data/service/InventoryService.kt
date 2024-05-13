package com.immobylette.appmobile.data.service

import com.immobylette.appmobile.data.dto.AgentDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface InventoryService {
    @POST("properties/{id}/start")
    suspend fun startInventory(@Path("id") id: UUID, @Body agent: AgentDto): Response<UUID>
}