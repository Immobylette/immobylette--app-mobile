package com.immobylette.appmobile.agent.selection

import java.net.URL
import java.util.UUID

data class AgentState (
    val id: UUID = UUID.randomUUID(),
    val surname: String = "",
    val name: String = "",
    val photo: URL = URL("https://www.immobylette.com/wp-content/uploads/2021/06/immobylette-logo.png"),
)