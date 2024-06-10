package com.immobylette.appmobile.inventory.summary

import com.immobylette.appmobile.data.dto.InventorySummaryDto

fun InventorySummaryDto.toState() = InventorySummaryState(
    nbRooms = nbRooms,
    nbPhotos = nbPhotos,
    date = date
)