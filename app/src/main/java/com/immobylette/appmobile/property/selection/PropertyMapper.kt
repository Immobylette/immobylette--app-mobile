package com.immobylette.appmobile.property.selection

import com.immobylette.appmobile.data.dto.PaginatedPropertiesDto
import com.immobylette.appmobile.data.dto.PropertyDto
import com.immobylette.appmobile.data.dto.PropertySummaryDto
import java.net.URL

fun PropertyDto.toState() = PropertyState(
    id = id,
    nbRooms = nbRooms,
    propertyType = propertyType,
    propertyClass = propertyClass,
    owner = owner.toFormattedString(),
    currentTenant = currentTenant?.toFormattedString(),
    address = address,
    currentInventory = currentInventory != null,
    currentInventoryId = currentInventory,
    photo = URL(photo)
)

fun PropertySummaryDto.toState() = PropertyState(
    id = id,
    propertyType = propertyType,
    propertyClass = propertyClass,
    address = address,
    currentInventory = currentInventory,
    photo = URL(photo),
    distance = distance
)

fun PaginatedPropertiesDto.toState() = PropertyListState(
    properties = content.map { it.toState() },
    pagination = PaginationState(
        page = pageable.pageNumber,
        perPage = pageable.pageSize,
        isLastPage = pageable.pageNumber == totalPages
    )
)