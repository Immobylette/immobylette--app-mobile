package com.immobylette.appmobile.agent.selection

import java.net.URL
import java.util.UUID

data class AgentState (
    val id: UUID,
    val surname: String,
    val name: String,
    val photo: URL
)