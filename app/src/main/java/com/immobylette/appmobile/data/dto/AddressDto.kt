package com.immobylette.appmobile.data.dto

import java.util.UUID

data class AddressDto(
    val id: UUID,
    val number: Int,
    val street: String,
    val zip: Int,
    val city: String,
    val floor: Int? = null,
    val extra: String? = null,
    val latitude: Float,
    val longitude: Float
){
    fun toFormattedString(floorString: String): String {
        return "$number $street${if (floor != null) " $floorString $floor" else ""}${if (extra != null) " $extra" else ""}, $zip $city"
    }
}
