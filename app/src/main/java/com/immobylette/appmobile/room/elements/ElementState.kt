package com.immobylette.appmobile.room.elements

import com.immobylette.appmobile.data.dto.PhotoUrlDto
import java.net.URL
import java.util.UUID

import com.immobylette.appmobile.data.enum.ElementState as ElementStateEnum

data class ElementState(
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val description: String? = "",
    val type: String = "",
    val photo: URL = URL("http://placekitten.com/200/300"), //TODO: Modify when the backend will retrieve photos,
    val attributes: Map<String, String> = mapOf(),
    val nbBasePhotos: Int = 0,
    val nbPreviousPhotos: Int = 0,
    val basePhotos: List<PhotoUrlDto> = listOf(),
    val previousPhotos: List<PhotoUrlDto> = listOf(),
    val state: ElementStateEnum = ElementStateEnum.NEW,
    val checked: Boolean = false,
    val step: StepState = StepState()
)
