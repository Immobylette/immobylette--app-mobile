package com.immobylette.appmobile.room.elements

import com.immobylette.appmobile.data.dto.PhotoDto
import com.immobylette.appmobile.data.enum.ElementState

data class StepState(
    val description: String? = "",
    val errorDescription: String? = "",
    val state: ElementState = ElementState.NEW,
    val photos: List<PhotoDto> = listOf(),
    val checked: Boolean = false,
)
