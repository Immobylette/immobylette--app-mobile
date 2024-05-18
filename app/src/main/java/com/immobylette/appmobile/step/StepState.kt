package com.immobylette.appmobile.step

import com.immobylette.appmobile.data.enum.ElementState
import com.immobylette.appmobile.data.model.Photo
import java.net.URL

data class StepState (
    val description: String = "",
    val errorDescription: String? = "",
    val state: ElementState = ElementState.NEW,
    val photos: MutableMap<URL, Photo> = mutableMapOf()
)