package com.immobylette.appmobile.step.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.immobylette.appmobile.data.model.Photo
import com.immobylette.appmobile.step.StepState
import java.io.File

class CurrentStepViewModel: ViewModel() {
    private val _currentStep = mutableStateOf(StepState())
    val currentState: State<StepState> get() = _currentStep

    fun addPhoto(photo: File, description: String) {
        val photos = _currentStep.value.photos;

        photos[photo.toURI().toURL()] = Photo(file = photo, description = description)

        _currentStep.value = _currentStep.value.copy(photos = photos)
    }
}