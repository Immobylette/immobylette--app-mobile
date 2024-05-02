package com.immobylette.appmobile.property.selection

data class PaginationState(
    val page: Int = -1,
    val perPage: Int = 10,
    val isLastPage: Boolean = false,
)
