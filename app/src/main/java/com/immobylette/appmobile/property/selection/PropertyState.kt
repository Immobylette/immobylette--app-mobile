package com.immobylette.appmobile.property.selection

import com.immobylette.appmobile.data.dto.AddressDto
import com.immobylette.appmobile.data.dto.ThirdPartyDto
import java.net.URL
import java.util.UUID

data class PropertyState(
    val id: UUID = UUID.randomUUID(),
    val nbRooms: Int = 0,
    val propertyType: String = "",
    val propertyClass: String = "",
    val owner: ThirdPartyDto = ThirdPartyDto(),
    val currentTenant: ThirdPartyDto? = ThirdPartyDto(),
    val address: AddressDto? = null,
    val currentInventory: Boolean = false,
    val currentInventoryId: UUID? = null,
    val photo: URL = URL("http://placekitten.com/200/300"),
    val distance: Float = 0f
)
