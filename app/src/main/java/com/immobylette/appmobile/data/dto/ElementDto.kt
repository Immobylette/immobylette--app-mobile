package com.immobylette.appmobile.data.dto

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.UUID

data class ElementDto(
    val id: UUID,
    val name: String,
    val description: String?,
    val type: String,
    val photo: URL,
    val attributes: Map<String, String>,

    @SerializedName("base_photos")
    val basePhotos: List<PhotoUrlDto>,

    @SerializedName("previous_photos")
    val previousPhotos: List<PhotoUrlDto>,
)
