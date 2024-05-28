package com.immobylette.appmobile.data.dto

import com.immobylette.appmobile.data.enum.ElementState

data class StepSentDto(
    val description: String?,
    val errorDescription: String?,
    val state: ElementState,
    val photosDescriptions: List<String>
)
