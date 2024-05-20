package com.immobylette.appmobile.data.dto

import com.immobylette.appmobile.data.enum.ElementState

data class StepDto(
    val description: String?,
    val errorDescription: String?,
    val state: ElementState,
    val photos: List<PhotoDto>,
    val checked: Boolean,
)
