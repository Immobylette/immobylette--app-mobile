package com.immobylette.appmobile.data.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class InventorySummaryDto(
    @SerializedName("nb_rooms")
    val nbRooms: Int,

    @SerializedName("nb_photos")
    val nbPhotos: Int,
    val date: Date
)
