package com.immobylette.appmobile.property.selection

data class PropertyListState(
    val properties: List<PropertyState> = listOf(),
    val pagination: PaginationState = PaginationState()
)
