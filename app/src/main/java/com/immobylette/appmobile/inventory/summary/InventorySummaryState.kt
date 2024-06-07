package com.immobylette.appmobile.inventory.summary

import java.util.Date

data class InventorySummaryState(
    val nbRooms: Int = 0,
    val nbPhotos: Int = 0,
    val date: Date = Date()
)
