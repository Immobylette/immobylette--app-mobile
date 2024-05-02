package com.immobylette.appmobile.data.dto

import java.util.UUID
import com.google.gson.annotations.SerializedName

data class PropertyDto(
    val id: UUID,
    val surface: Double,

    @SerializedName("nb_rooms")
    val nbRooms: Int,

    @SerializedName("hot_water_type")
    val hotWaterType: String,

    @SerializedName("heating_type")
    val heatingType: String,

    @SerializedName("property_type")
    val propertyType: String,

    @SerializedName("property_class")
    val propertyClass: String,

    @SerializedName("owner")
    val owner: ThirdPartyDto,

    @SerializedName("current_tenant")
    val currentTenant: ThirdPartyDto?,

    val address: AddressDto,

    @SerializedName("current_inventory")
    val currentInventory: UUID?,

    val photo: String
)
