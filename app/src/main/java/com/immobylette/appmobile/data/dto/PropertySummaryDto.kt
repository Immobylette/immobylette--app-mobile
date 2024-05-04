package com.immobylette.appmobile.data.dto

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class PropertySummaryDto(
    val id: UUID,

    @SerializedName("property_class")
    val propertyClass: String,

    @SerializedName("property_type")
    val propertyType: String,

    val address: AddressDto,

    @SerializedName("current_inventory")
    val currentInventory: Boolean,

    val distance: Float,
    val photo: String
)
