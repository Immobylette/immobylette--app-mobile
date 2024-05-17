package com.immobylette.appmobile.step

import com.immobylette.appmobile.data.model.Photo
import java.net.URL

data class StepState (
    val description: String = "",
    val errorDescription: String? = "",
    val state: String = "", // TODO mettre le type enum
    val photos: MutableMap<URL, Photo> = mutableMapOf()
)