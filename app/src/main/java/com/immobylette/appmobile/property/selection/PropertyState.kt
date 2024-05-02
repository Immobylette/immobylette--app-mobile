package com.immobylette.appmobile.property.selection

import java.net.URL
import java.util.UUID

data class PropertyState(
    val id: UUID = UUID.randomUUID(),
    val nbRooms: Int = 0,
    val propertyType: String = "",
    val propertyClass: String = "",
    val owner: String = "",
    val currentTenant: String? = "",
    val address: String = "",
    val currentInventory: Boolean = false,
    val photo: URL = URL("http://placekitten.com/200/300"), //TODO: Modify when the backend will retrieve photos
    val distance: Float = 0f
)