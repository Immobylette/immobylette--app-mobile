package com.immobylette.appmobile.agent.selection

import com.immobylette.appmobile.data.dto.ThirdPartyDto
import java.net.URL

fun ThirdPartyDto.toAgentState() = AgentState(
    id = id,
    name = name,
    surname = surname,
    photo = URL("https://picsum.photos/200/300")
)