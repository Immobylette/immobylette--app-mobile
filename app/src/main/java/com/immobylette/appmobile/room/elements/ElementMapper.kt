package com.immobylette.appmobile.room.elements

import com.immobylette.appmobile.data.dto.ElementDto
import com.immobylette.appmobile.data.dto.ElementSummaryDto
import com.immobylette.appmobile.data.enum.ElementState as ElementStateEnum

fun ElementSummaryDto.toState() = ElementState(
    id = id,
    name = name,
    description = description,
    type = type,
    attributes = attributes,
    nbBasePhotos = nbBasePhotos,
    nbPreviousPhotos = nbPreviousPhotos,
    state = ElementStateEnum.from(state) ?: ElementStateEnum.NEW,
    checked = checked
)

fun ElementDto.toState() = ElementState(
    id = id,
    name = name,
    description = description,
    type = type,
    attributes = attributes,
    basePhotos = basePhotos,
    previousPhotos = previousPhotos,
)