package com.immobylette.appmobile.room.elements

import com.immobylette.appmobile.data.dto.StepDto

fun StepDto.toState() = StepState(
    description = description,
    errorDescription = errorDescription,
    state = state,
    photos = photos,
    checked = checked,
)