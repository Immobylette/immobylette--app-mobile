package com.immobylette.appmobile.step

import androidx.compose.runtime.mutableStateListOf
import com.immobylette.appmobile.data.enum.ElementState
import com.immobylette.appmobile.data.model.Photo

data class StepState (
    val description: String = "",
    val errorDescription: String? = "",
    val state: ElementState = ElementState.NEW,
    val photos: MutableList<Photo> = mutableStateListOf(),
    val isWall: Boolean = false
)