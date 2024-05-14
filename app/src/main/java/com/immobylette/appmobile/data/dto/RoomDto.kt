package com.immobylette.appmobile.data.dto

import java.util.UUID
import com.google.gson.annotations.SerializedName

data class RoomDto(
    val id: UUID,
    val name: String,
    val description: String,

    @SerializedName("nb_order")
    val nbOrder: Int,

    val area: Double,

    @SerializedName("nb_walls")
    val nbWalls: Int,

    @SerializedName("nb_windows")
    val nbWindows: Int,

    @SerializedName("nb_doors")
    val nbDoors: Int,

    val reference: String?,

    val allocation: String?,

    @SerializedName("room_type")
    val roomType: String?,
)
