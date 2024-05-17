package com.immobylette.appmobile.room.gotoroom

import com.immobylette.appmobile.data.dto.RoomDto

fun RoomDto.toState() = RoomState(
    id = id,
    name = name,
    description = description,
    nbOrder = nbOrder,
    area = area,
    nbWalls = nbWalls,
    nbWindows = nbWindows,
    nbDoors = nbDoors,
    reference = reference,
    allocation = allocation,
    roomType = roomType
)