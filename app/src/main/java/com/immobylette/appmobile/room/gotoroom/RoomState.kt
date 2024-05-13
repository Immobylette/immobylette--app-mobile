package com.immobylette.appmobile.room.gotoroom

import java.util.UUID

data class RoomState(

    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val description: String = "",
    val nbOrder: Int = 0,
    val area: Double = 0.0,
    val nbWalls: Int = 0,
    val nbWindows: Int = 0,
    val nbDoors: Int = 0,
    val reference: String? = "",
    val allocation: String? = "",
    val roomType: String = "",
)