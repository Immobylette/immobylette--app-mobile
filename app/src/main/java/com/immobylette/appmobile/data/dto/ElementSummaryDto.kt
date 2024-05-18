package com.immobylette.appmobile.data.dto

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.util.UUID

data class ElementSummaryDto(
    val id: UUID,
    val name: String,
    val description: String?,
    val type: String,
    val photo: URL,
    val attributes: Map<String, String>,

    @SerializedName("nb_base_photos")
    val nbBasePhotos: Int,

    @SerializedName("nb_previous_photos")
    val nbPreviousPhotos: Int,

    val state: String,
    val checked: Boolean
)