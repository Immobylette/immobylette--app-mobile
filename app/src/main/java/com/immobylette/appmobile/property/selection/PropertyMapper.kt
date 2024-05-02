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
    address = address.toFormattedString(),
    currentInventory = currentInventory != null,
    photo = URL("https://picsum.photos/200/300") //TODO: Modify when the backend will retrieve photos
)

fun PropertySummaryDto.toState() = PropertyState(
    id = id,
    propertyType = propertyType,
    propertyClass = propertyClass,
    address = address.toFormattedString(),
    currentInventory = currentInventory,
    photo = URL("https://picsum.photos/200/300"), //TODO: Modify when the backend will retrieve photos
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