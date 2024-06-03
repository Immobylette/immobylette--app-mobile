package com.immobylette.appmobile.element.newstep

import com.immobylette.appmobile.data.dto.StepSentDto
import com.immobylette.appmobile.step.StepState

fun StepState.toDto() = StepSentDto(
    description = description,
    errorDescription = errorDescription,
    state = state,
    photosDescriptions = photos.map { photo -> photo.description }
)