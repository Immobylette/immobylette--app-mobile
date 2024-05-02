package com.immobylette.appmobile.data.dto

data class PaginatedPropertiesDto(
    val content: List<PropertySummaryDto>,
    val pageable: PaginationResponseDto,
    val totalElements: Int,
    val totalPages: Int,
)
