package com.immobylette.appmobile.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

import com.immobylette.appmobile.data.dto.RoomDto

interface RoomService {
    @GET("inventories/{id}/room")
    suspend fun getCurrentRoom(
        @Path("id") id: UUID
    ): Response<RoomDto>
}