package com.immobylette.appmobile.data.service

import com.immobylette.appmobile.data.dto.ThirdPartyDto
import retrofit2.http.GET

interface AgentService {
    @GET("third-parties/agents")
    suspend fun getAllAgents(): List<ThirdPartyDto>
}